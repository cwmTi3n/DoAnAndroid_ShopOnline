package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopdodientu.R;

public class DeliveredOrderInforActivity extends AppCompatActivity {
    private TextView tvBack, tvUserName, tvPhone, tvAddress, tvOrderCode, tvOrderTime, tvPickupTime;
    private ImageView imgSupport;
    private RecyclerView rcvProduct;
    private Button btnRepurchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivered_order_infor);

        MapItemView();
    }

    private void MapItemView(){
        tvBack = findViewById(R.id.tvBack);
        tvUserName = findViewById(R.id.tvUserName);
        tvPhone = findViewById(R.id.tvPhone);
        tvAddress = findViewById(R.id.tvAddress);
        tvOrderCode = findViewById(R.id.tvOrderCode);
        tvOrderTime = findViewById(R.id.tvOrderTime);
        tvPickupTime = findViewById(R.id.tvPickupTime);
        imgSupport = findViewById(R.id.imgSupport);
        rcvProduct = findViewById(R.id.rcvProduct);
        btnRepurchase = findViewById(R.id.btnRepurchase);
    }
}