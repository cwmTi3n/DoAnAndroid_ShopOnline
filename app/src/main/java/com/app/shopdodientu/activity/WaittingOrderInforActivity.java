package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.adapter.OrderAdapter;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.CartItemModel;
import com.app.shopdodientu.model.CartModel;
import com.app.shopdodientu.util.Constant;
import com.app.shopdodientu.util.UIHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaittingOrderInforActivity extends AppCompatActivity {
    private TextView tvBack, tvUserName, tvPhone, tvAddress, tvTotal, tvOrderTime, tvCancel, tvContact;
    private Button btnConfirmReceived, btnRepurchase;
    private ImageView imgSupport;
    private RecyclerView rcvProduct;
    private CartModel cartModel;
    private List<CartItemModel> cartItemModels;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        cartModel = (CartModel) getIntent().getSerializableExtra("cart");
        int status = cartModel.getStatus();
        if(status == 1) {
            setContentView(R.layout.activity_waitting_order_infor);
            tvCancel = findViewById(R.id.tvCancel);
            tvContact = findViewById(R.id.tvContact);
        }
        else if(status == 2) {
            setContentView(R.layout.activity_delivering_order_infor);
            btnConfirmReceived = findViewById(R.id.btnConfirmReceived);
        }
        else if(status == 3) {
            setContentView(R.layout.activity_delivered_order_infor);
            btnRepurchase = findViewById(R.id.btnRepurchase);
        }
        else if(status == 4) {
            setContentView(R.layout.activity_cancel_order_infor);
            btnRepurchase = findViewById(R.id.btnRepurchase);
        }
        MapItemView();
        renderView();
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        UIHelper.gotoSupport(imgSupport, this);
//        tvCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        tvContact.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(WaittingOrderInforActivity.this, ChatActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private void MapItemView(){
        tvBack = findViewById(R.id.tvBack);
        tvUserName = findViewById(R.id.tvUserName);
        tvPhone = findViewById(R.id.tvPhone);
        tvAddress = findViewById(R.id.tvAddress);
        tvTotal = findViewById(R.id.tvTotalPrice);
        tvOrderTime = findViewById(R.id.tvOrderTime);
        imgSupport = findViewById(R.id.imgSupport);
        rcvProduct = findViewById(R.id.rcvProduct);
    }

    private void setingViewByStatus() {
        int status = cartModel.getStatus();
        if(status == 1) {

        }
        else if(status == 2) {

        }
        else if(status == 3) {

        }
        else if(status == 4) {

        }
        //viết hàm chỉnh sửa giao diện theo waitting, delivering, delicered, cancel...;
    }
    private void renderView() {
        if(cartModel != null) {
            tvUserName.setText(Constant.userLogin.getUsername());
            tvAddress.setText(cartModel.getAddress());
            tvPhone.setText(Constant.userLogin.getPhone());
            tvOrderTime.setText(cartModel.getBuyDate());
            tvTotal.setText("₫"  + String.valueOf(cartModel.getSumPrice()));
        }

        ApiService apiService = ApiClient.getApiService();
        apiService.getItemsInCart(cartModel.getId())
                .enqueue(new Callback<List<CartItemModel>>() {
                    @Override
                    public void onResponse(Call<List<CartItemModel>> call, Response<List<CartItemModel>> response) {
                        cartItemModels = response.body();
                        if(cartItemModels != null) {
                            orderAdapter = new OrderAdapter(WaittingOrderInforActivity.this, cartItemModels);
                            rcvProduct.setHasFixedSize(true);
                            rcvProduct.setLayoutManager(new GridLayoutManager(WaittingOrderInforActivity.this, 1));
                            rcvProduct.setAdapter(orderAdapter);
                            orderAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CartItemModel>> call, Throwable t) {

                    }
                });
    }

}