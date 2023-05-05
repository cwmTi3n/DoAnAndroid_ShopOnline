package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopdodientu.R;

public class ProductDetailActivity extends AppCompatActivity {
    private ImageView imgProduct, imgAvatar;
    private TextView tvPrice, tvNameproduct, tvDescription, tvShopName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
    }
}