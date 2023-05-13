package com.app.shopdodientu.activity.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.UIHelper;

public class HistoryOrderActivity extends AppCompatActivity {

    private Spinner snStatusOrder;
    private TextView tvLatest, lineLatest, tvOldest, lineOldest;
    private LinearLayout linearOldest, linearLatest;
    private LinearLayout currentLinear;
    private TextView currentTextview, currentLine;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_history_order);

        MapItemView();
        AddItemsToSpinner();

        currentLinear = linearLatest;
        currentTextview = tvLatest;
        currentLine = lineLatest;
        LinearLatestClicked();
        LinearOldestClicked();


    }

    private void MapItemView(){
        tvLatest = (TextView) findViewById(R.id.tvLatest);
        tvOldest = (TextView) findViewById(R.id.tvOldest);
        lineLatest = (TextView) findViewById(R.id.lineLatest);
        lineOldest = (TextView) findViewById(R.id.lineOldest);
        linearLatest = (LinearLayout) findViewById(R.id.linearLatest);
        linearOldest = (LinearLayout) findViewById(R.id.linearOldest);
        snStatusOrder = (Spinner) findViewById(R.id.spinner);
    }

    private void AddItemsToSpinner(){
        String[] listCate = {"Status", "Successful", "Cancel", "Refund"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listCate);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snStatusOrder.setAdapter(adapter);
    }

    private void LinearLatestClicked(){
        linearLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLinear != linearLatest){
                    currentTextview.setTextColor(Color.parseColor("#716565"));
                    currentLine.setBackgroundColor(Color.parseColor("#716565"));
                }
                tvLatest.setTextColor(Color.parseColor("#ee4d2d"));
                lineLatest.setBackgroundColor(Color.parseColor("#ee4d2d"));
                currentLinear = linearLatest;
                currentLine = lineLatest;
                currentTextview = tvLatest;
            }
        });
    }

    private void LinearOldestClicked(){
        linearOldest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLinear != linearOldest){
                    currentTextview.setTextColor(Color.parseColor("#716565"));
                    currentLine.setBackgroundColor(Color.parseColor("#716565"));
                }
                tvOldest.setTextColor(Color.parseColor("#ee4d2d"));
                lineOldest.setBackgroundColor(Color.parseColor("#ee4d2d"));
                currentLinear = linearOldest;
                currentLine = lineOldest;
                currentTextview = tvOldest;
            }
        });
    }
}