package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.AddressTabLayout.LocationActivity;
import com.app.shopdodientu.util.UIHelper;

public class ChooseAddressActivity extends AppCompatActivity {
    private TextView tvBack, tvAddAddress;
    private RecyclerView rcvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_choose_address);

        MapItemView();
        TextViewAddClicked();
        TextViewBackClicked();
    }

    private void MapItemView(){
        tvBack = findViewById(R.id.tvBack);
        tvAddAddress = findViewById(R.id.tvAddAddress);
        rcvAddress = findViewById(R.id.rcvAddress);
    }

    private void TextViewAddClicked(){
        tvAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseAddressActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void TextViewBackClicked(){
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}