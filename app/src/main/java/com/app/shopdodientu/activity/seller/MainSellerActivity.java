package com.app.shopdodientu.activity.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.ShopTabLayout.HomeShopActivity;
import com.app.shopdodientu.activity.SupportActivity;
import com.app.shopdodientu.activity.seller.order.ShopOrderActivity;
import com.app.shopdodientu.util.Constant;
import com.app.shopdodientu.util.UIHelper;

public class MainSellerActivity extends AppCompatActivity {

    private TextView tvViewShop, tvAddProduct, tvSetUpStore, tvShopName, tvCheckingOrder, tvDeliverd, tvCancelled, tvFeedback;
    private ImageView imgSetting, imgSupport, imgAvatarStore;
    private LinearLayout linearMyProduct, linearFinance, linearCheckOrder;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_main_seller);

        MapItemView();
        UIHelper.gotoHomeShop(tvViewShop, this, Constant.userLogin.getId());
        gotoAddProduct();
        gotoSetUpStore();
        gotoShopProduct();
        gotoShopFinance();
        gotoShopOrder();
        gotoSupport();
    }

    private void gotoAddProduct() {
        tvAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(MainSellerActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });
    }
    private void gotoSetUpStore() {
        tvSetUpStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(MainSellerActivity.this, RegisterSellerActivity.class);
                startActivity(intent);
            }
        });

    }

    private void MapItemView(){
        imgSupport = findViewById(R.id.imgSupport);
        imgSetting = findViewById(R.id.imgSetting);
        imgAvatarStore = findViewById(R.id.imgAvatarStore);

        tvViewShop = (TextView) findViewById(R.id.tvViewShop);
        tvAddProduct = (TextView) findViewById(R.id.tvAddProduct);
        tvSetUpStore = (TextView) findViewById(R.id.tvSetUpStore);
        tvShopName = (TextView) findViewById(R.id.tvShopName);
        tvDeliverd = (TextView) findViewById(R.id.tvDeliverd);
        tvCancelled = (TextView) findViewById(R.id.tvCancelled);
        tvFeedback = (TextView) findViewById(R.id.tvFeedback);

        linearMyProduct = findViewById(R.id.linearMyProduct);
        linearFinance = findViewById(R.id.linearFinance);
        linearCheckOrder = findViewById(R.id.linearCheckOrder);
    }


    private void gotoSupport(){
        imgSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainSellerActivity.this, SupportActivity.class);
                startActivity(intent);
            }
        });
    }

    private void gotoShopProduct(){
        linearMyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainSellerActivity.this, HomeShopActivity.class);
                startActivity(intent);
            }
        });
    }

    private void gotoShopFinance(){
        linearFinance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainSellerActivity.this, FinanceActivity.class);
                startActivity(intent);
            }
        });
    }
    private void gotoShopOrder(){
        linearCheckOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainSellerActivity.this, ShopOrderActivity.class);
                startActivity(intent);
            }
        });
    }

}