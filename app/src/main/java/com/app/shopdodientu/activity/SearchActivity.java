package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.shopdodientu.R;

public class SearchActivity extends AppCompatActivity {

    private TextView tvRelated, tvlLatest, tvBestSeller;
    private TextView currentTextView;
    private Spinner snPrice;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        MapItemView();
        AddItemSpinnerPrice();

        currentTextView = tvRelated;
        tvRelated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTextView != tvRelated) {
                    if (currentTextView != null) {
                        currentTextView.setTypeface(null, Typeface.NORMAL);
                        currentTextView.setTextColor(Color.BLACK);
                    }
                    tvRelated.setTypeface(null, Typeface.BOLD);
                    tvRelated.setTextColor(Color.RED);
                    currentTextView = tvRelated;
                }
            }
        });

        tvlLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTextView != tvlLatest) {
                    if (currentTextView != null) {
                        currentTextView.setTypeface(null, Typeface.NORMAL);
                        currentTextView.setTextColor(Color.BLACK);
                    }
                    tvlLatest.setTypeface(null, Typeface.BOLD);
                    tvlLatest.setTextColor(Color.RED);
                    currentTextView = tvlLatest;
                }
            }
        });

        tvBestSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTextView != tvBestSeller) {
                    if (currentTextView != null) {
                        currentTextView.setTypeface(null, Typeface.NORMAL);
                        currentTextView.setTextColor(Color.BLACK);
                    }
                    tvBestSeller.setTypeface(null, Typeface.BOLD);
                    tvBestSeller.setTextColor(Color.RED);
                    currentTextView = tvBestSeller;
                }
            }
        });


        snPrice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();
//                currentTextView.setTypeface(null, Typeface.NORMAL);
//                currentTextView.setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do something when nothing is selected
            }
        });

    }

    private void MapItemView(){
        snPrice = findViewById(R.id.snprice);
        tvRelated = (TextView) findViewById(R.id.tvrelated);
        tvlLatest = (TextView) findViewById(R.id.tvlatest);
        tvBestSeller = (TextView) findViewById(R.id.tvbestseller);
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