package com.example.alicia.dostudy.Notes;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/*
* This code is from the following website: http://www.theappguruz.com/blog/android-take-photo-camera-gallery-code-sample
* This class is used to check the permission at runtime for reading the external storage.
* It will be used in the AddNoteActivity when the user wants to select a photo
* */

public class AddNotesUtiltiy {

    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private static final String PERMISSION_NECESSARY_TITLE = "Zugriff benötigt";
    private static final String PERMISSION_NECESSARY_MESSAGE = "Zugriff auf externen Speicher wird benötigt";
    private static final String MESSAGE_POSITIVE_BUTTON = "Ja";

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true)
                            .setTitle(PERMISSION_NECESSARY_TITLE)
                            .setMessage(PERMISSION_NECESSARY_MESSAGE)
                            .setPositiveButton(MESSAGE_POSITIVE_BUTTON, new DialogInterface.OnClickListener() {
                                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                                }
                            });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
}

