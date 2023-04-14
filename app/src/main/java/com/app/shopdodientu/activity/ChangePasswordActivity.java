package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.UIHelper;

public class ChangePasswordActivity extends AppCompatActivity {

    private ImageView imvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        MapItemView();
        UIHelper.backtoProfile(imvBack, this);

    }

    private void MapItemView(){
        imvBack = (ImageView) findViewById(R.id.imvBack);
    }

}