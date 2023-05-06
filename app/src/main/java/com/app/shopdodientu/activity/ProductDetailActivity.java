package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.model.ProductModel;
import com.bumptech.glide.Glide;

public class ProductDetailActivity extends AppCompatActivity {
    private ImageView imgProduct, imgAvatar;
    private TextView tvPrice, tvNameproduct, tvDescription, tvShopName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        MapItemView();
//        renderView();
    }

    private void MapItemView() {
        imgProduct = (ImageView) findViewById(R.id.imgproduct);
        imgAvatar = (ImageView) findViewById(R.id.imgavatar);
        tvPrice = (TextView) findViewById(R.id.tvprice);
        tvNameproduct = (TextView) findViewById(R.id.tvnameproduct);
        tvDescription = (TextView) findViewById(R.id.tvdescription);
        tvShopName = (TextView) findViewById(R.id.tvshopName);
    }

    private void renderView() {
        ProductModel productModel = (ProductModel) getIntent().getSerializableExtra("product");
        tvPrice.setText(String.valueOf(productModel.getPrice()));
        tvNameproduct.setText(productModel.getName());
        tvDescription.setText(productModel.getDescription());
        tvShopName.setText(productModel.getShopname());
        Glide.with(getApplicationContext())
                .load(productModel.getImage())
                .into(imgProduct);
        Glide.with(getApplicationContext())
                .load(productModel.getAvatarShop())
                .into(imgAvatar);
    }
}