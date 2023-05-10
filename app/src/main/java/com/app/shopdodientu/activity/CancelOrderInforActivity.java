package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.BaseBundle;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopdodientu.R;

public class CancelOrderInforActivity extends AppCompatActivity {

    private TextView tvBack, tvUserName, tvPhone, tvAddress, tvOrderCode, tvOrderTime, tvCancelTime;
    private RecyclerView rcvProduct;
    private Button btnRepurchase;
    private ImageView imgSupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_order_infor);

        MapItemView();

    }

    private void MapItemView(){
        tvBack = (TextView) findViewById(R.id.tvBack);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvOrderCode = (TextView) findViewById(R.id.tvOrderCode);
        tvOrderTime = (TextView) findViewById(R.id.tvOrderTime);
        tvCancelTime = (TextView) findViewById(R.id.tvCancelTime);
        rcvProduct = (RecyclerView) findViewById(R.id.rcvProduct);
        imgSupport = (ImageView) findViewById(R.id.imgSupport);

    }
}