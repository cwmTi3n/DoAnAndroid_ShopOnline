package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.OrderTabLayout.MyOrderActivity;
import com.app.shopdodientu.activity.seller.WelcomeSellerActivity;
import com.app.shopdodientu.model.UserModel;
import com.app.shopdodientu.util.Constant;
import com.app.shopdodientu.util.UIHelper;

public class MyAccountActivity extends AppCompatActivity {


    //BETWEEN
    private ImageView imgavatar;
    private TextView tvfullname, tvemail, tvHelp, tvChat, tvProfile, tvRegisterSeller, tvMyOrder;

    //BOTTTOM
    private LinearLayout linearHome, linearAccount, linearCart, linearSupport, linearLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_my_account);

        MapItemView();
        gotoProfile();

//        Intent intent = new Intent(MyAccountActivity.this, WelcomeSellerActivity.class);
//        startActivity(intent);
        UIHelper.gotoMainSeller(tvRegisterSeller, this);

        gotoMyOrder();
        //BOTTOM
        UIHelper.gotoCart(linearCart, this);
        UIHelper.gotoHome(linearHome, this);

    }

    private void MapItemView(){
        imgavatar = findViewById(R.id.imgavatar);
        tvfullname = findViewById(R.id.tvfullname);
        tvemail = findViewById(R.id.tvemail);


        tvProfile = (TextView) findViewById(R.id.tvProfile);
        tvRegisterSeller = (TextView) findViewById(R.id.tvRegisterStore);
        tvMyOrder = (TextView) findViewById(R.id.tvMyOrder);
        tvHelp = findViewById(R.id.tvHelp);
        tvChat = findViewById(R.id.tvChat);

        linearHome = (LinearLayout) findViewById(R.id.home);
        linearAccount = (LinearLayout) findViewById(R.id.account);
        linearCart = (LinearLayout) findViewById(R.id.cart);
        linearSupport = (LinearLayout) findViewById(R.id.support);
        linearLogout = (LinearLayout) findViewById(R.id.logout);
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

    private void gotoSupport() {
        tvHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(MyAccountActivity.this, SupportActivity.class);
                MyAccountActivity.this.startActivity(intent);
            }
        });
    }


}