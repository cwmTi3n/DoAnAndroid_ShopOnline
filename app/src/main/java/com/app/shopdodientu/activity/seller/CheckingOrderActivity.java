package com.app.shopdodientu.activity.seller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.UIHelper;

public class CheckingOrderActivity extends AppCompatActivity {

    private TextView tvBack;
    private ImageView imgSupport;
    private RecyclerView rcvCheckingOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_checking_order);

        MapItemView();
        BackClicked();
    }

    private void MapItemView() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        imgSupport = (ImageView) findViewById(R.id.imgSupport);
        rcvCheckingOrder = (RecyclerView) findViewById(R.id.rcvCheckingOrder);
    }

    private void BackClicked(){
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}