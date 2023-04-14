package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.R;

public class ProfileActivity extends AppCompatActivity {

    //declear bottom variables
    private ImageView imvHome, imvAccount, imvCart, imvSupport, imvLogOut;
    private TextView tvHome, tvAccount, tvCart, tvSupport, tvLogout;
    private LinearLayout linearHome, linearAccount, linearCart, linearSupport, linearLogout;
    private TextView currentTextViewBottom;
    private ImageView currentImgBottom;
    private LinearLayout currentLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        MapItemView();
        //BOTTOM
        currentTextViewBottom = tvAccount;
        currentImgBottom = imvAccount;
        currentLinear = linearAccount;
        LinearHomeClicked();
        LinearProfileClicked();
        LinearCartClicked();
        LinearSupportClicked();
        LinearLogoutClicked();
    }

    private void MapItemView() {
        imvHome = (ImageView) findViewById(R.id.imgHome);
        imvAccount = (ImageView) findViewById(R.id.imgProfile);
        imvCart = (ImageView) findViewById(R.id.imgCart);
        imvSupport = (ImageView) findViewById(R.id.imgSupport);
        imvLogOut = (ImageView) findViewById(R.id.imgLogout);
        tvHome = (TextView) findViewById(R.id.tvHome);
        tvAccount = (TextView) findViewById(R.id.tvProfile);
        tvCart = (TextView) findViewById(R.id.tvCart);
        tvSupport = (TextView) findViewById(R.id.tvSupport);
        tvLogout = (TextView) findViewById(R.id.tvLogout);
        linearHome = (LinearLayout) findViewById(R.id.home);
        linearAccount = (LinearLayout) findViewById(R.id.profile);
        linearCart = (LinearLayout) findViewById(R.id.cart);
        linearSupport = (LinearLayout) findViewById(R.id.support);
        linearLogout = (LinearLayout) findViewById(R.id.logout);
    }

    private void SetEffectLastTextViewBottom(TextView last){
        last.setTypeface(null, Typeface.NORMAL);
        last.setTextColor(Color.BLACK);
    }
    private void SetEffectCurrentTextViewBottom(TextView current){
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
    private void LinearHomeClicked (){
        linearHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLinear != linearHome){
                    if(currentLinear != null){
                        SetEffectLastTextViewBottom(currentTextViewBottom);
                        SetEffectLastImgBottom(currentImgBottom);
                    }
                    SetEffectCurrentTextViewBottom(tvHome);
                    SetEffectCurrentImgBottom(imvHome);
                    currentTextViewBottom = tvHome;
                    currentImgBottom = imvHome;
                    currentLinear = linearHome;
                }
            }
        });
    }

    private void LinearProfileClicked() {
        linearAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentLinear != linearAccount) {
                    if (currentLinear != null) {
                        SetEffectLastTextViewBottom(currentTextViewBottom);
                        SetEffectLastImgBottom(currentImgBottom);
                    }
                    SetEffectCurrentTextViewBottom(tvAccount);
                    SetEffectCurrentImgBottom(imvAccount);
                    currentTextViewBottom = tvAccount;
                    currentImgBottom = imvAccount;
                    currentLinear = linearAccount;
                }
            }
        });
    }

    private void LinearCartClicked() {

        linearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentLinear != linearCart) {
                    if (currentLinear != null) {
                        SetEffectLastTextViewBottom(currentTextViewBottom);
                        SetEffectLastImgBottom(currentImgBottom);
                    }
                    SetEffectCurrentTextViewBottom(tvCart);
                    SetEffectCurrentImgBottom(imvCart);
                    currentTextViewBottom = tvCart;
                    currentImgBottom = imvCart;
                    currentLinear = linearCart;
                }
            }
        });
    }

    private void LinearSupportClicked() {
        linearSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentLinear != linearSupport) {
                    if (currentLinear != null) {
                        SetEffectLastTextViewBottom(currentTextViewBottom);
                        SetEffectLastImgBottom(currentImgBottom);
                    }
                    SetEffectCurrentTextViewBottom(tvSupport);
                    SetEffectCurrentImgBottom(imvSupport);
                    currentTextViewBottom = tvSupport;
                    currentImgBottom = imvSupport;
                    currentLinear = linearSupport;
                }
            }
        });
    }

    private void LinearLogoutClicked() {
        linearLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentLinear != linearLogout) {
                    if (currentLinear != null) {
                        SetEffectLastTextViewBottom(currentTextViewBottom);
                        SetEffectLastImgBottom(currentImgBottom);
                    }
                    SetEffectCurrentTextViewBottom(tvLogout);
                    SetEffectCurrentImgBottom(imvLogOut);
                    currentTextViewBottom = tvLogout;
                    currentImgBottom = imvLogOut;
                    currentLinear = linearLogout;
                }
            }
        });
    }
}