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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.shopdodientu.R;

public class SearchActivity extends AppCompatActivity {

    private TextView tvRelated, tvLatest, tvBestSeller;
    private Spinner snPrice;
    private ImageView imvHome, imvProfile, imvCart, imvSupport, imvLogOut;
    private TextView tvHome, tvProfile, tvCart, tvSupport, tvLogout;
    private LinearLayout linearHome, linearProfile, linearCart, linearSupport, linearLogout;
    private TextView currentTextView, currentTextViewBottom;
    private ImageView currentImgBottom;
    private LinearLayout currentLinear;

    private
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        MapItemView();
        AddItemSpinnerPrice();
        SpinnerPriceClicked();

        //TOP
        currentTextView = tvRelated;
        TextViewBestSellerClicked();
        TextViewLatestClicked();
        TextViewRelatedClick();


        //BOTTOM
        LinearHomeClicked();
        LinearAccountClicked();
        LinearCartClicked();
        LinearSupportClicked();
        LinearLogoutClicked();

    }

    private void TextViewRelatedClick() {
        tvRelated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTextView != tvRelated) {
                    if (currentTextView != null) {
                        SetEffectLastTextViewTop(currentTextView);
                    }
                    SetEffectCurrentTextViewTop(tvRelated);
                    currentTextView = tvRelated;
                }
            }
        });
    }

    private void TextViewLatestClicked() {
        tvLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTextView != tvLatest) {
                    if (currentTextView != null) {
                        SetEffectLastTextViewTop(currentTextView);
                    }
                    SetEffectCurrentTextViewTop(tvLatest);
                    currentTextView = tvLatest;
                }
            }
        });
    }

    private void TextViewBestSellerClicked() {
        tvBestSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTextView != tvBestSeller) {
                    if (currentTextView != null) {
                        SetEffectLastTextViewTop(currentTextView);
                    }
                    SetEffectCurrentTextViewTop(tvBestSeller);
                    currentTextView = tvBestSeller;
                }
            }
        });
    }
    private void SpinnerPriceClicked(){
        snPrice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Do something when selected
                String selectedOption = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do something when nothing is selected
            }
        });
    }
    private void LinearHomeClicked (){
        linearHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void LinearAccountClicked() {
        linearProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, MyAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    private void LinearCartClicked() {

        linearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(SearchActivity.this, CartActivity.class);
//                startActivity(intent);
            }
        });
    }

    private void LinearSupportClicked() {
        linearSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(SearchActivity.this, SupportActivity.class);
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
    private void MapItemView(){
        snPrice = findViewById(R.id.snprice);
        tvRelated = (TextView) findViewById(R.id.tvrelated);
        tvLatest = (TextView) findViewById(R.id.tvlatest);
        tvBestSeller = (TextView) findViewById(R.id.tvbestseller);
        imvHome = (ImageView) findViewById(R.id.imgHome);
        imvProfile = (ImageView) findViewById(R.id.imgAccount);
        imvCart = (ImageView) findViewById(R.id.imgCart);
        imvSupport = (ImageView) findViewById(R.id.imgSupport);
        imvLogOut = (ImageView) findViewById(R.id.imgLogout);
        tvHome = (TextView) findViewById(R.id.tvHome);
        tvProfile = (TextView) findViewById(R.id.tvAccount);
        tvCart = (TextView) findViewById(R.id.tvCart);
        tvSupport = (TextView) findViewById(R.id.tvSupport);
        tvLogout = (TextView) findViewById(R.id.tvLogout);
        linearHome = (LinearLayout) findViewById(R.id.home);
        linearProfile = (LinearLayout) findViewById(R.id.account);
        linearCart = (LinearLayout) findViewById(R.id.cart);
        linearSupport = (LinearLayout) findViewById(R.id.support);
        linearLogout = (LinearLayout) findViewById(R.id.logout);

    }

    private void AddItemSpinnerPrice() {

        String[] listCate = {"High", "Low"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listCate);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snPrice.setAdapter(adapter);

    }

    private void SetEffectLastTextViewTop(TextView last){
        last.setTypeface(null, Typeface.NORMAL);
        last.setBackgroundResource(R.drawable.rectangle_border_black);
        last.setTextColor(Color.BLACK);
    }
    private void SetEffectCurrentTextViewTop(TextView current){
        current.setTypeface(null, Typeface.BOLD);
        current.setBackgroundResource(R.drawable.rectangle_border_orange_2dp);
        current.setTextColor(Color.parseColor("#FFA500"));
    }

}