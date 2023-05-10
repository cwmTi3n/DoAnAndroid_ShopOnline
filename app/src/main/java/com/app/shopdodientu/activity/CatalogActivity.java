package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.UIHelper;

import org.w3c.dom.Text;

public class CatalogActivity extends AppCompatActivity {

    private LinearLayout linearBannerShop, linearShop, linearProduct, linearCatalog;
    private ImageView imgAvatarShop;
    private TextView tvshopName, tvamountProduct, tvChat, tvSearch, tvShop, lineShop, tvProduct, lineProduct, tvCatalog, lineCatalog;
    private RecyclerView rcvCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        MapItemView();

    }
    private void MapItemView() {
        linearBannerShop = findViewById(R.id.linearBannerShop);
        linearShop = findViewById(R.id.linearShop);
        linearProduct = findViewById(R.id.linearProduct);
        linearCatalog = findViewById(R.id.linearCatalog);
        imgAvatarShop = findViewById(R.id.imgAvatarShop);
        tvshopName = findViewById(R.id.tvShopName);
        tvamountProduct = findViewById(R.id.tvamountProduct);
        tvChat = findViewById(R.id.tvChat);
        tvSearch = findViewById(R.id.tvSearch);
        tvShop = findViewById(R.id.tvShop);
        lineShop = findViewById(R.id.lineShop);
        tvProduct = findViewById(R.id.tvProduct);
        lineProduct = findViewById(R.id.lineProduct);
        tvCatalog = findViewById(R.id.tvCatalog);
        lineCatalog = findViewById(R.id.lineCatalog);
        rcvCategory = findViewById(R.id.rcvCategory);
    }
}