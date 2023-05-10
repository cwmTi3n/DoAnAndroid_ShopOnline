package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.app.shopdodientu.R;

public class ChooseAddressActivity extends AppCompatActivity {
    private TextView tvBack, tvAddAddress;
    private RecyclerView rcvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_address);

        MapItemView();
    }

    private void MapItemView(){
        tvBack = findViewById(R.id.tvBack);
        tvAddAddress = findViewById(R.id.tvAddress);
        rcvAddress = findViewById(R.id.rcvAddress);
    }
}