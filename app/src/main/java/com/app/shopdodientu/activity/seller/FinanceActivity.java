package com.app.shopdodientu.activity.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.StatisticsModel;
import com.app.shopdodientu.util.UIHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinanceActivity extends AppCompatActivity {

    private TextView tvBack, tvTotalOrder, tvDeliveringOrder, tvDeliveredOrder, tvCancelOrder, tvRevenue, tvRevenueBig, tvTotalOrderBig;
    private StatisticsModel statisticsModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_finance);

        MapItemView();
        getStatistics();

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void MapItemView(){
        tvBack = findViewById(R.id.tvBack);
        tvTotalOrder = findViewById(R.id.tvTotalOrder);
        tvDeliveringOrder = findViewById(R.id.tvDeliveringOrder);
        tvDeliveredOrder = findViewById(R.id.tvDeliveredOrder);
        tvCancelOrder = findViewById(R.id.tvCancelOrder);
        tvRevenue = findViewById(R.id.tvRevenue);
        tvRevenueBig = findViewById(R.id.tvRevenueBig);
        tvTotalOrderBig = findViewById(R.id.tvTotalOrderBig);
    }

    private void getStatistics(){
        ApiService apiService = ApiClient.getApiService();
        apiService.getStatistics()
                .enqueue(new Callback<StatisticsModel>() {
                    @Override
                    public void onResponse(Call<StatisticsModel> call, Response<StatisticsModel> response) {
                        statisticsModel = response.body();
                        tvTotalOrder.setText(String.valueOf(statisticsModel.getTotalOrder()));
                        tvDeliveredOrder.setText(String.valueOf(statisticsModel.getDeliveredOrder()));
                        tvDeliveringOrder.setText(String.valueOf(statisticsModel.getDeliveringOrder()));
                        tvCancelOrder.setText(String.valueOf(statisticsModel.getCancelOrder()));
                        tvRevenue.setText(String.valueOf(statisticsModel.getRevenue()));
                        tvRevenueBig.setText(String.valueOf(statisticsModel.getRevenue()));
                        tvTotalOrderBig.setText(String.valueOf(statisticsModel.getTotalOrder()));
                    }

                    @Override
                    public void onFailure(Call<StatisticsModel> call, Throwable t) {

                    }
                });
    }
}