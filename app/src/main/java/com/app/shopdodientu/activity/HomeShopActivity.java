package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.UIHelper;

import org.w3c.dom.Text;

public class HomeShopActivity extends AppCompatActivity {

    private LinearLayout linearShop, linearProduct, linearCatalog, linearBannerShop;
    private TextView tvshopName, tvamountProduct, tvChat, tvSearch;
    private ImageView imgAvatarShop;
    private GridView gvRecommend;
    private RecyclerView rcvLastProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_shop);

        MapItemView();

        UIHelper.gotoProductShop(linearProduct, this);
        UIHelper.gotoCatalogShop(linearCatalog, this);
    }

    private void MapItemView(){
        linearShop = (LinearLayout) findViewById(R.id.linearShop);
        linearProduct = (LinearLayout) findViewById(R.id.linearProduct);
        linearCatalog = (LinearLayout) findViewById(R.id.linearCatalog);
        linearBannerShop = findViewById(R.id.linearBannerShop);
        tvshopName = findViewById(R.id.tvshopName);
        tvamountProduct = findViewById(R.id.tvamountProduct);
        tvChat = findViewById(R.id.tvChat);
        tvSearch = findViewById(R.id.tvSearch);
        imgAvatarShop = findViewById(R.id.imgAvatarShop);
        gvRecommend = findViewById(R.id.gvRecommend);
        rcvLastProduct = findViewById(R.id.rcvLastProduct);
    }


}