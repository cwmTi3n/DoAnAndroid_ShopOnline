package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.UIHelper;

import org.w3c.dom.Text;

public class ProductActivity extends AppCompatActivity {
    private TextView tvShop;
    private LinearLayout linearCatalog;
    private TextView linePopulated, lineHot, lineLatest, tvPopulated, tvHot, tvLatest, tvPrice;
    private TextView currentTextView, currentLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        MapItemView();

        currentTextView = tvPopulated;
        currentLine = linePopulated;
        TextViewPopulatedClicked();
        TextViewHotClicked();
        TextViewLatestClicked();
        TextViewPriceClicked();

        UIHelper.gotoHomeShop(tvShop, this);
        UIHelper.gotoCatalogShop(linearCatalog, this);
    }

    private void MapItemView(){
        tvShop = (TextView) findViewById(R.id.tvShop);

        tvPopulated = (TextView) findViewById(R.id.tvPopular);
        tvHot = (TextView) findViewById(R.id.tvHot);
        tvLatest = (TextView) findViewById(R.id.tvLatest);
        tvPrice = (TextView) findViewById(R.id.tvPrice);

        linearCatalog = (LinearLayout) findViewById(R.id.linearCatalog);

        linePopulated = (TextView) findViewById(R.id.linePopular);
        lineHot = (TextView) findViewById(R.id.lineHot);
        lineLatest = (TextView) findViewById(R.id.lineLatest);

    }

    private void TextViewPopulatedClicked(){
        tvPopulated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentTextView != tvPopulated){
                    SetEffectLastTextViewTop(currentTextView);
                    SetEffectLineLastTextViewTop(currentLine);
                }
                if(currentTextView == tvPrice){
                    SetDrawablePriceMove();
                }
                SetEffectCurrentTextViewTop(tvPopulated);
                SetEffectLineCurrentTextViewTop(linePopulated);
                currentTextView = tvPopulated;
                currentLine = linePopulated;
            }
        });
    }

    private void TextViewHotClicked(){
        tvHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentTextView != tvHot){
                    SetEffectLastTextViewTop(currentTextView);
                    SetEffectLineLastTextViewTop(currentLine);
                }
                if(currentTextView == tvPrice){
                    SetDrawablePriceMove();
                }
                SetEffectCurrentTextViewTop(tvHot);
                SetEffectLineCurrentTextViewTop(lineHot);
                currentTextView = tvHot;
                currentLine = lineHot;
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