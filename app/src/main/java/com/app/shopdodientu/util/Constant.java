package com.app.shopdodientu.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.app.shopdodientu.model.UserModel;

public class Constant {
    public static final int MY_REQUEST_CODE = 100;
    public static UserModel userLogin;
    public static String[] storge_permissions = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
    };
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public static String[] storge_permissions_33 = {
            android.Manifest.permission.READ_MEDIA_IMAGES,
            android.Manifest.permission.READ_MEDIA_AUDIO,
            android.Manifest.permission.READ_MEDIA_VIDEO
    };
    public static String[] permissions(){
        String[] p;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            p = storge_permissions_33;
        }
        else {
            p = storge_permissions;
        }
        return p;
    }
}
