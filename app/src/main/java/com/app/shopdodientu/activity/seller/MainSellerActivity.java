package com.app.shopdodientu.activity.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.Constant;
import com.app.shopdodientu.util.UIHelper;

import java.util.Map;

public class MainSellerActivity extends AppCompatActivity {

    private TextView tvViewShop, tvAddProduct, tvSetUpStore, tvShopName, tvCheckingOrder, tvDeliverd, tvCancelled, tvFeedback;
    private ImageView imgSetting, imgSupport, imgAvatarStore;
    private LinearLayout linearMyProduct, linearFinance, linearHistoryOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_seller);

        MapItemView();
        UIHelper.gotoHomeShop(tvViewShop, this, Constant.userLogin.getId());
        UIHelper.gotoAddProduct(tvAddProduct, this);
        UIHelper.gotoSetUpStore(tvSetUpStore, this);
    }

    private void MapItemView(){
        imgSupport = findViewById(R.id.imgSupport);
        imgSetting = findViewById(R.id.imgSetting);
        imgAvatarStore = findViewById(R.id.imgAvatarStore);

        tvViewShop = (TextView) findViewById(R.id.tvViewShop);
        tvAddProduct = (TextView) findViewById(R.id.tvAddProduct);
        tvSetUpStore = (TextView) findViewById(R.id.tvSetUpStore);
        tvShopName = (TextView) findViewById(R.id.tvShopName);
        tvCheckingOrder = (TextView) findViewById(R.id.tvCheckingOrder);
        tvDeliverd = (TextView) findViewById(R.id.tvDeliverd);
        tvCancelled = (TextView) findViewById(R.id.tvCancelled);
        tvFeedback = (TextView) findViewById(R.id.tvFeedback);

        linearMyProduct = findViewById(R.id.linearMyProduct);
        linearFinance = findViewById(R.id.linearFinance);
        linearHistoryOrder = findViewById(R.id.linearHistoryOrder);
    }
}