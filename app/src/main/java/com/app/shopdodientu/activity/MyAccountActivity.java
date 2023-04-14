package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

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

        //BOTTOM
        currentTextViewBottom = tvHome;
        currentImgBottom = imvHome;
        currentLinearBottom = linearHome;
        LinearHomeClicked();
        LinearAccountClicked();
        LinearCartClicked();
        LinearSupportClicked();
        LinearLogoutClicked();
    }

    private void MapItemView(){
        tvHomeTop = (TextView) findViewById(R.id.tvHomeTop);
        tvStoreTop = (TextView) findViewById(R.id.tvStore);
        tvCartTop = (TextView) findViewById(R.id.tvCartTop);
        linearHomeTop = (LinearLayout) findViewById(R.id.linearHomeTop);
        linearStoreTop = (LinearLayout) findViewById(R.id.linearStoreTop);
        linearCartTop = (LinearLayout) findViewById(R.id.linearCartTop);

        tvProfile = (TextView) findViewById(R.id.tvProfile);
        tvRegisterSeller = (TextView) findViewById(R.id.tvRegisterSeller);

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
    private void SetEffectLastImgBottom(ImageView last){
        Bitmap bitmap;
        if (imvHome.equals(last)) {
            imvHome.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn1);
            imvHome.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }
        else if (imvAccount.equals((last))){
            imvAccount.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn2);
            imvAccount.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }
        else if (imvCart.equals((last))){
            imvCart.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn3);
            imvCart.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }
        else if (imvSupport.equals((last))){
            imvSupport.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn4);
            imvSupport.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }
        else {
            imvLogOut.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn5);
            imvLogOut.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }

    }

    private void SetEffectCurrentImgBottom(ImageView current){
        Bitmap bitmap;
        if (imvHome.equals(current)) {
            imvHome.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn1_color);
            imvHome.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }
        else if (imvAccount.equals((current))){
            imvAccount.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn2_color);
            imvAccount.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }
        else if (imvCart.equals((current))){
            imvCart.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn3_color);
            imvCart.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }
        else if (imvSupport.equals((current))){
            imvSupport.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn4_color);
            imvSupport.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }
        else {
            imvLogOut.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn5_color);
            imvLogOut.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }

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
                if(currentLinearBottom != linearHome){
                    if(currentLinearBottom != null){
                        SetEffectLastTextView(currentTextViewBottom);
                        SetEffectLastImgBottom(currentImgBottom);
                    }
                    SetEffectCurrentTextView(tvHome);
                    SetEffectCurrentImgBottom(imvHome);
                    currentTextViewBottom = tvHome;
                    currentImgBottom = imvHome;
                    currentLinearBottom = linearHome;
                }
            }
        });
    }

    private void LinearAccountClicked() {
        linearAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentLinearBottom != linearAccount) {
                    if (currentLinearBottom != null) {
                        SetEffectLastTextView(currentTextViewBottom);
                        SetEffectLastImgBottom(currentImgBottom);
                    }
                    SetEffectCurrentTextView(tvAccount);
                    SetEffectCurrentImgBottom(imvAccount);
                    currentTextViewBottom = tvAccount;
                    currentImgBottom = imvAccount;
                    currentLinearBottom = linearAccount;
                }
            }
        });
    }

    private void LinearCartClicked() {

        linearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentLinearBottom != linearCart) {
                    if (currentLinearBottom != null) {
                        SetEffectLastTextView(currentTextViewBottom);
                        SetEffectLastImgBottom(currentImgBottom);
                    }
                    SetEffectCurrentTextView(tvCart);
                    SetEffectCurrentImgBottom(imvCart);
                    currentTextViewBottom = tvCart;
                    currentImgBottom = imvCart;
                    currentLinearBottom = linearCart;
                }
            }
        });
    }

    private void LinearSupportClicked() {
        linearSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentLinearBottom != linearSupport) {
                    if (currentLinearBottom != null) {
                        SetEffectLastTextView(currentTextViewBottom);
                        SetEffectLastImgBottom(currentImgBottom);
                    }
                    SetEffectCurrentTextView(tvSupport);
                    SetEffectCurrentImgBottom(imvSupport);
                    currentTextViewBottom = tvSupport;
                    currentImgBottom = imvSupport;
                    currentLinearBottom = linearSupport;
                }
            }
        });
    }

    private void LinearLogoutClicked() {
        linearLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentLinearBottom != linearLogout) {
                    if (currentLinearBottom != null) {
                        SetEffectLastTextView(currentTextViewBottom);
                        SetEffectLastImgBottom(currentImgBottom);
                    }
                    SetEffectCurrentTextView(tvLogout);
                    SetEffectCurrentImgBottom(imvLogOut);
                    currentTextViewBottom = tvLogout;
                    currentImgBottom = imvLogOut;
                    currentLinearBottom = linearLogout;
                }
            }
        });
    }
}