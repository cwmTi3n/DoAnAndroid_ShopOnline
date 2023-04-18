package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.UIHelper;

public class HomeShopActivity extends AppCompatActivity {

    private LinearLayout linearShop, linearProduct, linearCatalog;

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
    }


}