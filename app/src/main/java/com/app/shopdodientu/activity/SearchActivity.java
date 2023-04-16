package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shopdodientu.R;

import org.w3c.dom.Text;

public class SearchActivity extends AppCompatActivity {

    private TextView tvRelated, tvLatest, tvBestSeller, tvPrice;
    private TextView lineRelated, lineLatest, lineBest, lineHoriRelated, lineHoriLatest, lineHoriBest, lineHoriPrice;
    private LinearLayout linearHome, linearAccount, linearCart, linearSupport, linearLogout;
    private TextView currentTextView, currentLine, currentLineHori;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        MapItemView();

        //TOP
        currentTextView = tvRelated;
        currentLine = lineRelated;
        currentLineHori = lineHoriRelated;
        TextViewBestSellerClicked();
        TextViewLatestClicked();
        TextViewRelatedClick();
        TextViewPriceClicked();


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
                        SetEffectLineLastTextViewTop(currentLine, currentLineHori);
                    }
                    if(currentTextView == tvPrice){
                        SetDrawablePriceMove();
                    }
                    SetEffectCurrentTextViewTop(tvRelated);
                    SetEffectLineCurrentTextViewTop(lineRelated, lineHoriRelated);
                    currentTextView = tvRelated;
                    currentLine = lineRelated;
                    currentLineHori = lineHoriRelated;
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
                        SetEffectLineLastTextViewTop(currentLine, currentLineHori);
                    }
                    if(currentTextView == tvPrice){
                        SetDrawablePriceMove();
                    }
                    SetEffectCurrentTextViewTop(tvLatest);
                    SetEffectLineCurrentTextViewTop(lineLatest, lineHoriLatest);
                    currentTextView = tvLatest;
                    currentLine = lineLatest;
                    currentLineHori = lineHoriLatest;
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
                        SetEffectLineLastTextViewTop(currentLine, currentLineHori);
                    }
                    if(currentTextView == tvPrice){
                        SetDrawablePriceMove();
                    }
                    SetEffectCurrentTextViewTop(tvBestSeller);
                    SetEffectLineCurrentTextViewTop(lineBest, lineHoriBest);
                    currentTextView = tvBestSeller;
                    currentLine = lineBest;
                    currentLineHori = lineHoriBest;
                }
            }
        });
    }

    private void TextViewPriceClicked(){
        tvPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentTextView != null) {
                    SetEffectLastTextViewTop(currentTextView);
                    SetEffectLineLastTextViewTop(currentLine, currentLineHori);
                }
                SetDrawablePriceClicked();
                SetEffectCurrentTextViewTop(tvPrice);
                lineHoriPrice.setBackgroundColor(Color.parseColor("#FFA500"));
                currentTextView = tvPrice;
                currentLineHori = lineHoriPrice;
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
        linearAccount.setOnClickListener(new View.OnClickListener() {
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
        tvRelated = (TextView) findViewById(R.id.tvrelated);
        tvLatest = (TextView) findViewById(R.id.tvlatest);
        tvBestSeller = (TextView) findViewById(R.id.tvbestseller);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        lineRelated = (TextView) findViewById(R.id.lineRelated);
        lineLatest = (TextView) findViewById(R.id.lineLatest);
        lineBest = (TextView) findViewById(R.id.lineBestseller);

        lineHoriRelated = (TextView) findViewById(R.id.lineHoriRelated);
        lineHoriLatest = (TextView) findViewById(R.id.lineHoriLatest);
        lineHoriBest = (TextView) findViewById(R.id.lineHoriBest);
        lineHoriPrice = (TextView) findViewById(R.id.lineHoriPrice);

        linearHome = (LinearLayout) findViewById(R.id.home);
        linearAccount = (LinearLayout) findViewById(R.id.account);
        linearCart = (LinearLayout) findViewById(R.id.cart);
        linearSupport = (LinearLayout) findViewById(R.id.support);
        linearLogout = (LinearLayout) findViewById(R.id.logout);

    }



    private void SetEffectLastTextViewTop(TextView last){
        last.setTypeface(null, Typeface.NORMAL);
        last.setTextColor(Color.parseColor("#8B7C7C"));
    }
    private void SetEffectCurrentTextViewTop(TextView current){
        current.setTypeface(null, Typeface.BOLD);
        current.setTextColor(Color.parseColor("#FFA500"));
    }
    private void SetEffectLineCurrentTextViewTop(TextView line, TextView lineHori){
        line.setBackgroundColor(Color.parseColor("#FFA500"));
        lineHori.setBackgroundColor(Color.parseColor("#FFA500"));
    }
    private void SetEffectLineLastTextViewTop(TextView line, TextView lineHori) {
        line.setBackgroundColor(Color.parseColor("#F1E6E6"));
        lineHori.setBackgroundColor(Color.parseColor("#F1E6E6"));
    }
    private void SetDrawablePriceClicked(){
        Drawable[] drawables = tvPrice.getCompoundDrawables();
        Drawable rightDrawable = drawables[2];
        Drawable myDrawable = getResources().getDrawable(R.drawable.unfold);
        Drawable drawableUpArrow = getResources().getDrawable(R.drawable.up_arrow);
        Drawable drawableDownArrow = getResources().getDrawable(R.drawable.down_arrow);


        if (rightDrawable != null && myDrawable != null && rightDrawable.getConstantState().equals(myDrawable.getConstantState())) {
            tvPrice.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawableUpArrow, null);

        } else if (rightDrawable != null && drawableUpArrow != null && rightDrawable.getConstantState().equals(drawableUpArrow.getConstantState())){
            tvPrice.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawableDownArrow, null);

        } else if (rightDrawable != null && drawableDownArrow != null && rightDrawable.getConstantState().equals(drawableDownArrow.getConstantState())){
            tvPrice.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawableUpArrow, null);
        }
    }
    private void SetDrawablePriceMove(){
        Drawable drawableRight = getResources().getDrawable(R.drawable.unfold);
        tvPrice.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawableRight , null);
    }
}