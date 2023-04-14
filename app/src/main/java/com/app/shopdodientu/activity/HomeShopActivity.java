package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.R;

public class HomeShopActivity extends AppCompatActivity {

    private TextView tvShop, tvProduct, tvCatalog;
    private TextView lineShop, lineProduct, lineCatalog;
    private LinearLayout linearShop, linearProduct, linearCatalog;

    private TextView currentTextView, currentLine;
    private LinearLayout currentLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_shop);

        MapItemView();

        //Effect
        currentTextView = tvShop;
        currentLine = lineShop;
        currentLinearLayout = linearShop;
        LinearShopClicked();
        LinearProductClicked();
        LinearCatalogClicked();
    }

    private void MapItemView(){
        tvShop = (TextView) findViewById(R.id.tvShop);
        tvProduct = (TextView) findViewById(R.id.tvProduct);
        tvCatalog = (TextView) findViewById(R.id.tvCatalog);
        lineShop = (TextView) findViewById(R.id.lineShop);
        lineProduct = (TextView) findViewById(R.id.lineProduct);
        lineCatalog = (TextView) findViewById(R.id.lineCatalog);
        linearShop = (LinearLayout) findViewById(R.id.linearShop);
        linearProduct = (LinearLayout) findViewById(R.id.linearProduct);
        linearCatalog = (LinearLayout) findViewById(R.id.linearCatalog);
    }

    @SuppressLint("ResourceType")
    private void SetEffectLastTextView(TextView last, TextView line){
        last.setTextColor(Color.parseColor("#938585"));
        line.setBackgroundResource(Color.parseColor("#F1E6E6"));
    }

    @SuppressLint("ResourceType")
    private void SetEffectCurrentTextView(TextView current, TextView line) {
        current.setTextColor(Color.parseColor("#FFA500"));
        line.setBackgroundResource(Color.parseColor("#FFA500"));
    }
    private void LinearShopClicked(){
        linearShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLinearLayout != linearShop){
                    SetEffectLastTextView(currentTextView, currentLine);
                }
                SetEffectCurrentTextView(tvShop, lineShop);
                currentTextView = tvShop;
                currentLine = lineShop;
                currentLinearLayout = linearShop;
            }
        });
    }

    private void LinearProductClicked(){
        linearProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLinearLayout != linearProduct){
                    SetEffectLastTextView(currentTextView, currentLine);
                }
                SetEffectCurrentTextView(tvProduct, lineProduct);
                currentTextView = tvProduct;
                currentLine = lineProduct;
                currentLinearLayout = linearProduct;
            }
        });
    }

    private void LinearCatalogClicked(){
        linearCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLinearLayout != linearCatalog){
                    SetEffectLastTextView(currentTextView, currentLine);
                }
                SetEffectCurrentTextView(tvCatalog, lineCatalog);
                currentTextView = tvCatalog;
                currentLine = lineCatalog;
                currentLinearLayout = linearCatalog;
            }
        });

    }
}