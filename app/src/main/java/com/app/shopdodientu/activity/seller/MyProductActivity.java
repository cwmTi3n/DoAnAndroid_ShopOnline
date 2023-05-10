package com.app.shopdodientu.activity.seller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.UIHelper;

import org.w3c.dom.Text;

public class MyProductActivity extends AppCompatActivity {
    private RecyclerView rcvProduct;
    private ImageView imvSearch;
    private TextView tvBack, tvLatest, tvBest, tvCatalog;
    private TextView lineLatest, lineBest, lineCatalog;
    private LinearLayout linearLatest, linearBest, linearCatalog;
    private TextView currentTextView, currentLine;
    private LinearLayout currentLinear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_product);

        MapItemView();
        UIHelper.gotoSearchByImageView(imvSearch, this);

        currentTextView = tvLatest;
        currentLine = lineLatest;
        currentLinear = linearLatest;
        LinearCatalogClicked();
        LinearBestSellerClicked();
        LinearLatestClicked();
    }

    private void MapItemView(){
        tvBack = (TextView) findViewById(R.id.tvBack);
        imvSearch = (ImageView) findViewById(R.id.imgSearch);
        tvLatest = (TextView) findViewById(R.id.tvLatest);
        tvBest = (TextView) findViewById(R.id.tvBestSeller);
        tvCatalog = (TextView) findViewById(R.id.tvCatalog);
        lineLatest = (TextView) findViewById(R.id.lineLatest);
        lineBest = (TextView) findViewById(R.id.lineBestSeller);
        lineCatalog = (TextView) findViewById(R.id.lineCatalog);
        linearLatest = (LinearLayout) findViewById(R.id.linearLatest);
        linearBest = (LinearLayout) findViewById(R.id.linearBestSeller);
        linearCatalog = (LinearLayout) findViewById(R.id.linearCatalog);
        rcvProduct = (RecyclerView) findViewById(R.id.rcvProduct);
    }

    private void SetEffectLastChoice(TextView tv, TextView line) {
        tv.setTextColor(Color.parseColor("#E87B7777"));
        tv.setTypeface(null, Typeface.NORMAL);
        line.setBackgroundColor(Color.parseColor("#F1E6E6"));
    }

    private void SetEffectCurrentChoice(TextView tv, TextView line) {
        tv.setTextColor(Color.parseColor("#fb5533"));
        tv.setTypeface(null, Typeface.BOLD);
        line.setBackgroundColor(Color.parseColor("#fb5533"));
    }

    private void LinearLatestClicked(){
        linearLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLinear != linearLatest) {
                    SetEffectLastChoice(currentTextView, currentLine);
                }
                SetEffectCurrentChoice(tvLatest, lineLatest);
                currentTextView = tvLatest;
                currentLine = lineLatest;
                currentLinear = linearLatest;
            }
        });
    }

    private void LinearBestSellerClicked(){
        linearBest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLinear != linearBest) {
                    SetEffectLastChoice(currentTextView, currentLine);
                }
                SetEffectCurrentChoice(tvBest, lineBest);
                currentTextView = tvBest;
                currentLine = lineBest;
                currentLinear = linearBest;
            }
        });
    }

    private void LinearCatalogClicked(){
        linearCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLinear != linearCatalog) {
                    SetEffectLastChoice(currentTextView, currentLine);
                }
                SetEffectCurrentChoice(tvCatalog, lineCatalog);
                currentTextView = tvCatalog;
                currentLine = lineCatalog;
                currentLinear = linearCatalog;
            }
        });
    }


}