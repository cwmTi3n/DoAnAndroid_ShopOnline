package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.UIHelper;

public class ProfileActivity extends AppCompatActivity {

    private Button btnChangepass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        MapItemView();

        UIHelper.gotoChangePassword(btnChangepass, this);

        //BOTTOM
    }

    private void MapItemView() {
        btnChangepass = (Button) findViewById(R.id.btnchangepass);
    }

}