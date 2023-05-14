package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.UIHelper;

public class DeliveringOrderInforActivity extends AppCompatActivity {
    private TextView tvBack, tvUserName, tvPhone, tvAddress, tvOrderCode, tvOrderTime;
    private ImageView imgSupport;
    private RecyclerView rcvProduct;
    private Button btnConfirmReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_delivering_order_infor);

        MapItemView();

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        UIHelper.gotoSupport(imgSupport, this);
        btnConfirmReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void MapItemView(){
        tvBack = findViewById(R.id.tvBack);
        tvUserName = findViewById(R.id.tvUserName);
        tvPhone = findViewById(R.id.tvPhone);
        tvAddress = findViewById(R.id.tvAddress);
        tvOrderCode = findViewById(R.id.tvOrderCode);
        tvOrderTime = findViewById(R.id.tvOrderTime);
        imgSupport = findViewById(R.id.imgSupport);
        rcvProduct = findViewById(R.id.rcvProduct);
        btnConfirmReceived = findViewById(R.id.btnConfirmReceived);
    }

}