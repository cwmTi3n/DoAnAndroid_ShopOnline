package com.app.shopdodientu.activity.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.app.shopdodientu.R;

public class FinanceActivity extends AppCompatActivity {

    private TextView tvBack, tvTotalOrder, tvDeliveringOrder, tvDeliveredOrder, tvCancelOrder, tvRevenue, tvRevenueBig, tvTotalOrderBig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);

        MapItemView();
    }

    private void MapItemView(){
        tvBack = findViewById(R.id.tvBack);
        tvTotalOrder = findViewById(R.id.tvTotalOrder);
        tvDeliveringOrder = findViewById(R.id.tvDeliveringOrder);
        tvDeliveredOrder = findViewById(R.id.tvDeliveredOrder);
        tvCancelOrder = findViewById(R.id.tvCancelOrder);
        tvRevenue = findViewById(R.id.tvRevenue);
        tvRevenueBig = findViewById(R.id.tvRevenueBig);
        tvTotalOrderBig = findViewById(R.id.tvTotalOrder);
    }
}