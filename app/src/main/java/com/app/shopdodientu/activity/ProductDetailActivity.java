package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.model.ProductModel;
import com.bumptech.glide.Glide;

public class ProductDetailActivity extends AppCompatActivity {
    private SearchView searchView;
    private RatingBar ratingBar;
    private ImageView imgProduct, imgAvatar;
    private TextView tvAmountSelled, tvPrice, tvNameproduct, tvDescription, tvShopName, tvamountProduct, tvviewShop, tvBuyNow;
    private RecyclerView rcvFeedback;
    private LinearLayout linearChat, linearAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        MapItemView();
        renderView();
    }

    private void MapItemView() {
        searchView = findViewById(R.id.searchView);
        ratingBar = findViewById(R.id.ratingBar);
        imgProduct = (ImageView) findViewById(R.id.imgproduct);
        imgAvatar = (ImageView) findViewById(R.id.imgavatar);
        tvAmountSelled = findViewById(R.id.tvAmountSelled);
        tvPrice = (TextView) findViewById(R.id.tvprice);
        tvNameproduct = (TextView) findViewById(R.id.tvnameproduct);
        tvDescription = (TextView) findViewById(R.id.tvdescription);
        tvShopName = (TextView) findViewById(R.id.tvshopName);
        tvamountProduct = findViewById(R.id.tvamountProduct);
        tvviewShop = findViewById(R.id.tvviewShop);
        rcvFeedback = findViewById(R.id.rcvFeedback);
        linearChat = findViewById(R.id.linearchat);
        linearAddToCart = findViewById(R.id.linearAddToCart);
        tvBuyNow = findViewById(R.id.tvBuyNow);
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