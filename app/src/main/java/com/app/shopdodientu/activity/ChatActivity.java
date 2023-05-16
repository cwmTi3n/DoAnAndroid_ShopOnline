package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.UIHelper;

public class ChatActivity extends AppCompatActivity {

    private TextView tvEmail, tvPhone, tvAddress;
    private ImageView imvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_chat);

        MapItemView();
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void MapItemView(){
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvAddress = findViewById(R.id.tvAddress);
        imvBack = findViewById(R.id.imvBack);
    }
}