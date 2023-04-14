package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.UiAutomation;
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
import com.app.shopdodientu.util.UIHelper;

public class MyAccountActivity extends AppCompatActivity {
    //TOP
    private TextView tvHomeTop, tvStoreTop, tvCartTop;
    private LinearLayout linearHomeTop, linearStoreTop, linearCartTop;
    private LinearLayout currentLinearTop;
    private TextView currentTextViewTop;

    //BETWEEN
    private TextView tvProfile, tvRegisterSeller;

    //BOTTTOM
    private ImageView imvHome, imvAccount, imvCart, imvSupport, imvLogOut;
    private TextView tvHome, tvAccount, tvCart, tvSupport, tvLogout;
    private LinearLayout linearHome, linearAccount, linearCart, linearSupport, linearLogout;
    private TextView currentTextViewBottom;
    private ImageView currentImgBottom;
    private LinearLayout currentLinearBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        MapItemView();
        //TOP
        currentTextViewTop = tvHomeTop;
        currentLinearTop = linearHomeTop;
        LinearHomeTopClicked();
        LinearStoreTopClicked();
        LinearCartTopClicked();

        //BETWEEN
        UIHelper.gotoWelcomeStore(tvRegisterSeller, this);
        UIHelper.gotoProfile(tvProfile, this);

        //BOTTOM

    }

    private void MapItemView(){
        tvHomeTop = (TextView) findViewById(R.id.tvHomeTop);
        tvStoreTop = (TextView) findViewById(R.id.tvStore);
        tvCartTop = (TextView) findViewById(R.id.tvCartTop);
        linearHomeTop = (LinearLayout) findViewById(R.id.linearHomeTop);
        linearStoreTop = (LinearLayout) findViewById(R.id.linearStoreTop);
        linearCartTop = (LinearLayout) findViewById(R.id.linearCartTop);

        tvProfile = (TextView) findViewById(R.id.tvProfile);
        tvRegisterSeller = (TextView) findViewById(R.id.tvRegisterStore);

        imvHome = (ImageView) findViewById(R.id.imgHome);
        imvAccount = (ImageView) findViewById(R.id.imgAccount);
        imvCart = (ImageView) findViewById(R.id.imgCart);
        imvSupport = (ImageView) findViewById(R.id.imgSupport);
        imvLogOut = (ImageView) findViewById(R.id.imgLogout);
        tvHome = (TextView) findViewById(R.id.tvHome);
        tvAccount = (TextView) findViewById(R.id.tvAccount);
        tvCart = (TextView) findViewById(R.id.tvCart);
        tvSupport = (TextView) findViewById(R.id.tvSupport);
        tvLogout = (TextView) findViewById(R.id.tvLogout);
        linearHome = (LinearLayout) findViewById(R.id.home);
        linearAccount = (LinearLayout) findViewById(R.id.account);
        linearCart = (LinearLayout) findViewById(R.id.cart);
        linearSupport = (LinearLayout) findViewById(R.id.support);
        linearLogout = (LinearLayout) findViewById(R.id.logout);
    }

    //EFFECT
    private void SetEffectLastTextView(TextView last){
        last.setTypeface(null, Typeface.NORMAL);
        last.setTextColor(Color.BLACK);
    }
    private void SetEffectCurrentTextView(TextView current){
        current.setTypeface(null, Typeface.BOLD);
        current.setTextColor(Color.parseColor("#FFA500"));
    }

    //TOP
    private void LinearHomeTopClicked(){
        linearHomeTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLinearTop != linearHomeTop){
                    SetEffectLastTextView(currentTextViewTop);
                }
                SetEffectCurrentTextView(tvHomeTop);
                currentTextViewTop = tvHomeTop;
                currentLinearTop = linearHomeTop;
            }
        });
    }

    private void LinearStoreTopClicked(){
        linearStoreTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLinearTop != linearStoreTop){
                    SetEffectLastTextView(currentTextViewTop);
                }
                SetEffectCurrentTextView(tvStoreTop);
                currentTextViewTop = tvStoreTop;
                currentLinearTop = linearStoreTop;
            }
        });
    }
    private void LinearCartTopClicked(){
        linearCartTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLinearTop != linearCartTop){
                    SetEffectLastTextView(currentTextViewTop);
                }
                SetEffectCurrentTextView(tvCartTop);
                currentTextViewTop = tvCartTop;
                currentLinearTop = linearCartTop;
            }
        });
    }

    //BETWEEN
    private void TextViewProfileClick(){
        tvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAccountActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void TextViewRegisterStoreClicked(){
        tvRegisterSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAccountActivity.this, WelcomeSellerActivity.class);
                startActivity(intent);
            }
        });
    }

    //BOTTOM
    private void LinearHomeClicked (){
        linearHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAccountActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void LinearCartClicked() {

        linearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MyAccountActivity.this, CartActivity.class);
//                startActivity(intent);
            }
        });
    }

    private void LinearSupportClicked() {
        linearSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MyAccountActivity.this, SupportActivity.class);
//                startActivity(intent);
            }
        });
    }

    private void LinearLogoutClicked() {
        linearLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}