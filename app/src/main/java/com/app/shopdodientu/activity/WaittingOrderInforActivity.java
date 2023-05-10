package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopdodientu.R;

public class WaittingOrderInforActivity extends AppCompatActivity {
    private TextView tvBack, tvUserName, tvPhone, tvAddress, tvOrderCode, tvOrderTime, tvCancel, tvContact;
    private ImageView imgSupport;
    private RecyclerView rcvProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitting_order_infor);

        MapItemView();
    }

    private void MapItemView(){
        tvBack = findViewById(R.id.tvBack);
        tvUserName = findViewById(R.id.tvUserName);
        tvPhone = findViewById(R.id.tvPhone);
        tvAddress = findViewById(R.id.tvAddress);
        tvOrderCode = findViewById(R.id.tvOrderCode);
        tvOrderTime = findViewById(R.id.tvOrderTime);
        tvCancel = findViewById(R.id.tvCancel);
        tvContact = findViewById(R.id.tvContact);
        imgSupport = findViewById(R.id.imgSupport);
        rcvProduct = findViewById(R.id.rcvProduct);
    }
}