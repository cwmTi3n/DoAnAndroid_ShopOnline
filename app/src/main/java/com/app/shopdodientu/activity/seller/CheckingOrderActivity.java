package com.app.shopdodientu.activity.seller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopdodientu.R;

public class CheckingOrderActivity extends AppCompatActivity {

    private TextView tvBack;
    private ImageView imgSupport;
    private RecyclerView rcvCheckingOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking_order);

        MapItemView();
    }

    private void MapItemView() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        imgSupport = (ImageView) findViewById(R.id.imgSupport);
        rcvCheckingOrder = (RecyclerView) findViewById(R.id.rcvCheckingOrder);
    }
}