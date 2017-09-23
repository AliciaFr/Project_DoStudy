package com.example.alicia.dostudy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class TakePictureActivity extends AppCompatActivity {
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    private Camera camera;
    private ImageAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCamera();
        initUI();
    }

    private void initCamera() {
        camera = new Camera(this);
    }

    private void initUI() {
        setContentView(R.layout.picture_activity);
        Point displaySize = getDisplaySize();

        GridView grid = (GridView) findViewById(R.id.photo_grid);
        gridAdapter = new ImageAdapter(this, displaySize);
        grid.setAdapter(gridAdapter);

        Button cameraButton = (Button) findViewById(R.id.picture_take);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
        Button selectedPictureButton = (Button) findViewById(R.id.picture_add);
        selectedPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFromGallery();
            }
        });
    }


    private void takePicture() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, 0);
        Intent intent = new Intent();
        intent.putExtra(getResources().getString(R.string.take_picture_intent), camera.getCurrentPhotoPath());
        setResult(RESULT_OK, intent);
        Toast.makeText(this, "Bild gespeichert", Toast.LENGTH_SHORT)
                .show();
    }

    private void chooseFromGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 1);
    }

    private void processPicture(String path) {
        Point imageSize = new Point(getDisplaySize().x / 2, getDisplaySize().y / 2);
        Bitmap image = camera.getScaledBitmap(path, imageSize);

        gridAdapter.addImage(image);
        gridAdapter.notifyDataSetChanged();
    }

    private Point getDisplaySize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            processPicture(camera.getCurrentPhotoPath());
        }

    }
}