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
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.StatisticsModel;
import com.app.shopdodientu.util.Constant;
import com.app.shopdodientu.util.UIHelper;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainSellerActivity extends AppCompatActivity {

    private TextView tvViewShop, tvAddProduct, tvSetUpStore, tvShopName, tvCheckingOrder, tvDeliverd, tvCancelled, tvDelivering;
    private ImageView imgSetting, imgSupport, imgAvatarStore;
    private LinearLayout linearMyProduct, linearFinance, linearCheckOrder;
    private Intent intent;
    private StatisticsModel statisticsModel;

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
        renderView();
        getStatistics();
        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(MainSellerActivity.this, RegisterSellerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void renderView() {
        tvShopName.setText(Constant.userLogin.getUsername());
        Glide.with(getApplicationContext())
                .load(Constant.userLogin.getAvatar())
                .into(imgAvatarStore);
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
        tvDelivering = (TextView) findViewById(R.id.tvDelivering);

        linearMyProduct = findViewById(R.id.linearMyProduct);
        linearFinance = findViewById(R.id.linearFinance);
        linearCheckOrder = findViewById(R.id.linearCheckOrder);
    }


    private void getStatistics(){
        ApiService apiService = ApiClient.getApiService();
        apiService.getStatistics()
                .enqueue(new Callback<StatisticsModel>() {
                    @Override
                    public void onResponse(Call<StatisticsModel> call, Response<StatisticsModel> response) {
                        statisticsModel = response.body();
                        tvDeliverd.setText(String.valueOf(statisticsModel.getDeliveredOrder()));
                        tvDelivering.setText(String.valueOf(statisticsModel.getDeliveringOrder()));
                        tvCancelled.setText(String.valueOf(statisticsModel.getCancelOrder()));
                    }

                    @Override
                    public void onFailure(Call<StatisticsModel> call, Throwable t) {

                    }
                });
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
                intent.putExtra("sellerId", Constant.userLogin.getId());
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