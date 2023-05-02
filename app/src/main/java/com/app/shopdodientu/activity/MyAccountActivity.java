package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.UiAutomation;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.seller.RegisterSellerActivity;
import com.app.shopdodientu.activity.seller.WelcomeSellerActivity;
import com.app.shopdodientu.model.UserModel;
import com.app.shopdodientu.util.UIHelper;

public class MyAccountActivity extends AppCompatActivity {
    //TOP
    private LinearLayout linearHomeTop, linearStoreTop;

    //BETWEEN
    private TextView tvProfile, tvRegisterSeller, tvMyOrder;

    //BOTTTOM
    private LinearLayout linearHome, linearAccount, linearCart, linearSupport, linearLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        MapItemView();
        //TOP


        //BETWEEN
        UIHelper.gotoWelcomeStore(tvRegisterSeller, this);
//        UIHelper.gotoProfile(tvProfile, this);
        gotoProfile();
        UIHelper.gotoMainSellerByLinear(linearStoreTop, this);
//        UIHelper.gotoMyOrder(tvMyOrder, this);
        gotoMyOrder();
        //BOTTOM
        UIHelper.gotoCart(linearCart, this);
        UIHelper.gotoHome(linearHome, this);

    }

    private void gotoProfile() {
        tvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAccountActivity.this, ProfileActivity.class);
                intent.putExtra("user", (UserModel)getIntent().getSerializableExtra("user"));
                MyAccountActivity.this.startActivity(intent);
            }
        });
    }

    private void gotoMyOrder() {
        tvMyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(MyAccountActivity.this, MyOrderActivity.class);
                MyAccountActivity.this.startActivity(intent);
            }
        });
    }

    private void MapItemView(){

        linearHomeTop = (LinearLayout) findViewById(R.id.linearHomeTop);
        linearStoreTop = (LinearLayout) findViewById(R.id.linearStoreTop);

        tvProfile = (TextView) findViewById(R.id.tvProfile);
        tvRegisterSeller = (TextView) findViewById(R.id.tvRegisterStore);
        tvMyOrder = (TextView) findViewById(R.id.tvMyOrder);

        linearHome = (LinearLayout) findViewById(R.id.home);
        linearAccount = (LinearLayout) findViewById(R.id.account);
        linearCart = (LinearLayout) findViewById(R.id.cart);
        linearSupport = (LinearLayout) findViewById(R.id.support);
        linearLogout = (LinearLayout) findViewById(R.id.logout);
    }


}