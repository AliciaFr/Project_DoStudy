package com.example.alicia.dostudy;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by Alicia on 20.09.2017.
 */

class AddLocationActivity {

    public boolean checkLocationPermission () {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
    }
}
