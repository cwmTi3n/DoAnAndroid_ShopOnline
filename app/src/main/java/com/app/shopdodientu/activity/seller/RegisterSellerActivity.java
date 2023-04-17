package com.app.shopdodientu.activity.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.UIHelper;

public class RegisterSellerActivity extends AppCompatActivity {

    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_seller);

        MapItemView();
        UIHelper.gotoMainSellerByButton(btnSave, this);
    }

    private void MapItemView(){
        btnSave = (Button) findViewById(R.id.btnSave);
    }
}