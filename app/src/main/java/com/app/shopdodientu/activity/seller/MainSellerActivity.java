package com.app.shopdodientu.activity.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.UIHelper;

import java.util.Map;

public class MainSellerActivity extends AppCompatActivity {

    private TextView tvViewShop, tvAddProduct, tvSetUpStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_seller);

        MapItemView();
        UIHelper.gotoHomeShop(tvViewShop, this);
        UIHelper.gotoAddProduct(tvAddProduct, this);
        UIHelper.gotoSetUpStore(tvSetUpStore, this);
    }

    private void MapItemView(){
        tvViewShop = (TextView) findViewById(R.id.tvViewShop);
        tvAddProduct = (TextView) findViewById(R.id.tvAddProduct);
        tvSetUpStore = (TextView) findViewById(R.id.tvSetUpStore);
    }
}