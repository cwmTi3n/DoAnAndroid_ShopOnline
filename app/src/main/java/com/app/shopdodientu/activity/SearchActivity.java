package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
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
    ImageView x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        MapItemView();
        //AddItemSpinnerPrice();

        currentTextView = tvRelated;

        //TOP
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


//        snPrice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedOption = parent.getItemAtPosition(position).toString();
////                currentTextView.setTypeface(null, Typeface.NORMAL);
////                currentTextView.setTextColor(Color.BLACK);
//    }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // Do something when nothing is selected
//            }
//        });

        //BOTTOM
        currentTextViewBottom = tvHome;
        currentImgBottom = imvHome;
        currentLinear = linearHome;

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
        linearProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLinear != linearProfile){
                    if(currentLinear != null){
                        SetEffectLastTextViewBottom(currentTextViewBottom);
                        SetEffectLastImgBottom(currentImgBottom);
                    }
                    SetEffectCurrentTextViewBottom(tvProfile);
                    SetEffectCurrentImgBottom(imvProfile);
                    currentTextViewBottom = tvProfile;
                    currentImgBottom = imvProfile;
                    currentLinear = linearProfile;
                }
            }
        });

        linearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLinear != linearCart){
                    if(currentLinear != null){
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

        linearSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLinear != linearSupport){
                    if(currentLinear != null){
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

        linearLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLinear != linearLogout){
                    if(currentLinear != null){
                        SetEffectLastTextViewBottom(currentTextViewBottom);
                        SetEffectLastImgBottom(currentImgBottom);
                    }
                    SetEffectCurrentTextViewBottom(tvLatest);
                    SetEffectCurrentImgBottom(imvLogOut);
                    currentTextViewBottom = tvLatest;
                    currentImgBottom = imvLogOut;
                    currentLinear = linearLogout;
                }
            }
        });
    }

    private void MapItemView(){
        //snPrice = findViewById(R.id.snprice);
        tvRelated = (TextView) findViewById(R.id.tvrelated);
        tvLatest = (TextView) findViewById(R.id.tvlatest);
        tvBestSeller = (TextView) findViewById(R.id.tvbestseller);
        imvHome = (ImageView) findViewById(R.id.imgHome);
        imvProfile = (ImageView) findViewById(R.id.imgProfile);
        imvCart = (ImageView) findViewById(R.id.imgCart);
        imvSupport = (ImageView) findViewById(R.id.imgSupport);
        imvLogOut = (ImageView) findViewById(R.id.imgLogout);
        tvHome = (TextView) findViewById(R.id.tvHome);
        tvProfile = (TextView) findViewById(R.id.tvProfile);
        tvCart = (TextView) findViewById(R.id.tvCart);
        tvSupport = (TextView) findViewById(R.id.tvSupport);
        tvLogout = (TextView) findViewById(R.id.tvLogout);
        linearHome = (LinearLayout) findViewById(R.id.home);
        linearProfile = (LinearLayout) findViewById(R.id.profile);
        linearCart = (LinearLayout) findViewById(R.id.cart);
        linearSupport = (LinearLayout) findViewById(R.id.support);
        linearLogout = (LinearLayout) findViewById(R.id.logout);

    }

    private void SetEffectLastTextViewTop(TextView last){
        last.setTypeface(null, Typeface.NORMAL);
        last.setBackgroundResource(R.drawable.rectangle_border_black);
        last.setTextColor(Color.BLACK);
    }
    private void SetEffectCurrentTextViewTop(TextView current){
        current.setTypeface(null, Typeface.BOLD);
        current.setBackgroundResource(R.drawable.rectangle_border_orange_3dp);
        current.setTextColor(Color.parseColor("#FFA500"));
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
        else if (imvProfile.equals((last))){
            imvProfile.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn2);
            imvProfile.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
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
        else if (imvProfile.equals((current))){
            imvProfile.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn2_color);
            imvProfile.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
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

    private void AddItemSpinnerPrice() {

        String[] listCate = {"Price", "High", "Low"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listCate);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snPrice.setAdapter(adapter);


        snPrice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();
                // Do something with the selected option
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do something when nothing is selected
            }
        });
    }
}