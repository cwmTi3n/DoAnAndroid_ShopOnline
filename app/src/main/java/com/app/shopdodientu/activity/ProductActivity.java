package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.UIHelper;

import org.w3c.dom.Text;

public class ProductActivity extends AppCompatActivity {

    private LinearLayout linearBannerShop;
    private TextView tvshopName, tvamountProduct, tvChat, tvSearch;
    private TextView tvShop;
    private LinearLayout linearShop, linearProduct, linearCatalog;
    private TextView lineRelated, lineLatest, lineBest, tvRelated, tvLatest, tvBest, tvPrice;
    private TextView currentTextView, currentLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        MapItemView();

        currentTextView = tvRelated;
        currentLine = lineRelated;
        TextViewRelatedClicked();
        TextViewBestClicked();
        TextViewLatestClicked();
        TextViewPriceClicked();

        UIHelper.gotoHomeShop(tvShop, this);
        UIHelper.gotoCatalogShop(linearCatalog, this);
    }

    private void MapItemView(){
        linearBannerShop = findViewById(R.id.linearBannerShop);
        tvshopName = findViewById(R.id.tvshopName);
        tvamountProduct = findViewById(R.id.tvamountProduct);
        tvChat = findViewById(R.id.tvChat);
        tvSearch = findViewById(R.id.tvSearch);
        linearShop = findViewById(R.id.linearShop);
        linearProduct = findViewById(R.id.linearProduct);
        linearCatalog = (LinearLayout) findViewById(R.id.linearCatalog);
        tvShop = (TextView) findViewById(R.id.tvShop);
        tvRelated = (TextView) findViewById(R.id.tvRelated);
        tvLatest = (TextView) findViewById(R.id.tvLatest);
        tvBest = (TextView) findViewById(R.id.tvBest);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        lineRelated = (TextView) findViewById(R.id.lineRelated);
        lineLatest = (TextView) findViewById(R.id.lineLatest);
        lineBest = (TextView) findViewById(R.id.lineBest);

    }

    private void TextViewRelatedClicked(){
        tvRelated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentTextView != tvRelated){
                    SetEffectLastTextViewTop(currentTextView);
                    SetEffectLineLastTextViewTop(currentLine);
                }
                if(currentTextView == tvPrice){
                    SetDrawablePriceMove();
                }
                SetEffectCurrentTextViewTop(tvRelated);
                SetEffectLineCurrentTextViewTop(lineRelated);
                currentTextView = tvRelated;
                currentLine = lineRelated;
            }
        });
    }

    private void TextViewLatestClicked(){
        tvLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentTextView != tvLatest){
                    SetEffectLastTextViewTop(currentTextView);
                    SetEffectLineLastTextViewTop(currentLine);
                }
                if(currentTextView == tvPrice){
                    SetDrawablePriceMove();
                }
                SetEffectCurrentTextViewTop(tvLatest);
                SetEffectLineCurrentTextViewTop(lineLatest);
                currentTextView = tvLatest;
                currentLine = lineLatest;
            }
        });
    }

    private void TextViewBestClicked(){
        tvBest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentTextView != tvBest){
                    SetEffectLastTextViewTop(currentTextView);
                    SetEffectLineLastTextViewTop(currentLine);
                }
                if(currentTextView == tvPrice){
                    SetDrawablePriceMove();
                }
                SetEffectCurrentTextViewTop(tvBest);
                SetEffectLineCurrentTextViewTop(lineBest);
                currentTextView = tvBest;
                currentLine = lineBest;
            }
        });
    }

    private void TextViewPriceClicked(){
        tvPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentTextView != null) {
                    SetEffectLastTextViewTop(currentTextView);
                    SetEffectLineLastTextViewTop(currentLine);
                }
                SetDrawablePriceClicked();
                SetEffectCurrentTextViewTop(tvPrice);
                currentTextView = tvPrice;
            }
        });
    }

    private void SetEffectLastTextViewTop(TextView last){
        last.setTypeface(null, Typeface.NORMAL);
        last.setTextColor(Color.parseColor("#8B7C7C"));
    }
    private void SetEffectCurrentTextViewTop(TextView current){
        current.setTypeface(null, Typeface.BOLD);
        current.setTextColor(Color.parseColor("#FFA500"));
    }
    private void SetEffectLineCurrentTextViewTop(TextView line){
        line.setBackgroundColor(Color.parseColor("#FFA500"));
    }
    private void SetEffectLineLastTextViewTop(TextView line) {
        line.setBackgroundColor(Color.parseColor("#F1E6E6"));
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