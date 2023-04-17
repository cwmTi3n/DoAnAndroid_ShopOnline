package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.UIHelper;

import org.w3c.dom.Text;

public class CatalogActivity extends AppCompatActivity {
    private TextView tvShop;

    private LinearLayout linearProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        MapItemView();
        UIHelper.gotoHomeShop(tvShop, this);
        UIHelper.gotoProductShop(linearProduct, this);
    }
    private void MapItemView() {
        tvShop = (TextView) findViewById(R.id.tvShop);
        linearProduct = (LinearLayout) findViewById(R.id.linearProduct);
    }
}