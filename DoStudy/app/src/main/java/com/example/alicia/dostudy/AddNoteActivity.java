package com.example.alicia.dostudy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alicia.dostudy.Notes.NotesDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/*
* This codes uses the takeImage functions of the following website: http://www.theappguruz.com/blog/android-take-photo-camera-gallery-code-sample
* */
public class AddNoteActivity extends AppCompatActivity {

    private EditText editTitle, editLecture, editNote;
    private TextView addDate, selectedDate, addPicture;
    private Button addButton;
    private ImageView ivImage;

    private NotesDatabase database;

    private String filePathImage;

    private static final int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initDB();
        initUI();
        initDatePicker();
        initTakePicture();
        initAddButton();
    }

    private void initDB() {
        database = new NotesDatabase(this);
    }

    private void initUI() {
        editTitle = (EditText) findViewById(R.id.add_note_title);
        editLecture = (EditText) findViewById(R.id.add_note_lecture);
        editNote = (EditText) findViewById(R.id.add_note);
        addDate = (TextView) findViewById(R.id.add_note_add_date);
        selectedDate = (TextView) findViewById(R.id.add_note_date);
        addPicture = (TextView) findViewById(R.id.add_note_add_picture);
        addButton = (Button) findViewById(R.id.add_note_button);
        ivImage = (ImageView) findViewById(R.id.add_note_image);
    }

    private void initDatePicker() {
        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }

    private void initTakePicture() {
        addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
    }

    private void initAddButton() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTitle.getText().toString();
                String lecture = editLecture.getText().toString();
                String date = selectedDate.getText().toString();
                String note = editNote.getText().toString();

                if (title.equals("")) {
                    Toast toast = Toast.makeText(AddNoteActivity.this, getResources().getString(R.string.toast_not_all_fields), Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    editTitle.setText("");
                    editLecture.setText("");
                    selectedDate.setText("");
                    editNote.setText("");

                    addNote(title, lecture, date, note, filePathImage);
                    Toast toastAdded = Toast.makeText(AddNoteActivity.this, getResources().getString(R.string.toast_note_added), Toast.LENGTH_SHORT);
                    toastAdded.show();
                }
            }
        });
    }

    private void addNote(String title, String lecture, String date, String note, String filePathImage) {
        database.insertNotes(title, lecture, date, note, filePathImage);
    }

    private void showDatePickerDialog() {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getFragmentManager(), getResources().getString(R.string.date_picker));
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            TextView dateValue = (TextView) getActivity().findViewById(R.id.add_note_date);
            GregorianCalendar date = new GregorianCalendar(year, month, day);
            java.text.DateFormat dateformat = java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT, Locale.GERMANY);
            String dateToString = dateformat.format(date.getTime());
            dateValue.setText(dateToString);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResults) {
        switch (requestCode) {
            case AddNotesUtiltiy.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo")) {
                        startCameraIntent();
                    } else if (userChoosenTask.equals("Choose from Library")) {
                        startGalleryIntent();
                    } else {
                        // code for deny
                    }
                    break;
                }
        }
    }

    private void selectImage() {
        final CharSequence[] items = {
                "Take Photo", "Choose from Library", "Cancel"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(AddNoteActivity.this);
        builder.setTitle("Füge ein Foto hinzu")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        boolean result = AddNotesUtiltiy.checkPermission(AddNoteActivity.this);

                        if (items[item].equals("Take Photo")) {
                            userChoosenTask = "Take Photo";
                            if (result) {
                                startCameraIntent();
                            }
                        } else if (items[item].equals("Choose from Library")) {
                            userChoosenTask = "Choose from Library";
                            if (result) {
                                startGalleryIntent();
                            }
                        } else if (items[item].equals("Cancel")) {
                            dialogInterface.dismiss();
                        }
                    }
                })
                .show();
    }

    private void startGalleryIntent() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galleryIntent, "Select File"), SELECT_FILE);
    }

    private void startCameraIntent() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                onSelectFromGalleryResult(data);
            } else if (requestCode == REQUEST_CAMERA) {
                onCaptureImageResult(data);
            }
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + "jpg");
        filePathImage = destination.getAbsolutePath();

        FileOutputStream fileOutput;
        try {
            destination.createNewFile();
            fileOutput = new FileOutputStream(destination);
            fileOutput.write(bytes.toByteArray());
            fileOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ivImage.setImageBitmap(thumbnail);
    }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        if (data != null) {
            Uri selectedImageUri = data.getData();
            ivImage.setImageURI(selectedImageUri);
            filePathImage = getPathFromUri(this.getApplicationContext(), selectedImageUri);
        }
    }

    private String getPathFromUri(Context context, Uri selectedImageUri) {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver( ).query(selectedImageUri, proj, null, null, null);
        if(cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(proj[0]);
                result = cursor.getString(column_index);
            }
            cursor.close();
        }
        if(result == null) {
            result = "Not found";
        }
        return result;
    }
}
