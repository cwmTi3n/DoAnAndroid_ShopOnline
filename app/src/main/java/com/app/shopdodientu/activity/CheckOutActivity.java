package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopdodientu.R;

public class CheckOutActivity extends AppCompatActivity {
    private TextView tvBack, tvUserName, tvPhone, tvAddress, tvAmount, tvTemporaryPrice, tvTotal, tvTotalBottom, tvCheckOut;
    private RecyclerView rcvProduct;
    private ImageView imgEditAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        MapItemView();
    }

    private void MapItemView(){
        tvBack = findViewById(R.id.tvBack);
        tvUserName = findViewById(R.id.tvUserName);
        tvPhone = findViewById(R.id.tvPhone);
        tvAddress = findViewById(R.id.tvAddress);
        tvAmount = findViewById(R.id.tvAmount);
        tvTemporaryPrice = findViewById(R.id.tvTemporaryPrice);
        tvTotal = findViewById(R.id.tvTotal);
        tvTotalBottom = findViewById(R.id.tvTotalBottom);
        tvCheckOut = findViewById(R.id.tvCheckOut);
        rcvProduct = findViewById(R.id.rcvProduct);
        imgEditAddress = findViewById(R.id.imgEditAddress);
    }
}