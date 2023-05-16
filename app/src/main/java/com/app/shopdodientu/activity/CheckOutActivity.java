package com.app.shopdodientu.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.AddressTabLayout.LocationActivity;
import com.app.shopdodientu.activity.OrderTabLayout.MyOrderActivity;
import com.app.shopdodientu.adapter.OrderAdapter;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.CartItemModel;
import com.app.shopdodientu.model.CheckoutModel;
import com.app.shopdodientu.model.UserModel;
import com.app.shopdodientu.util.Constant;
import com.app.shopdodientu.util.LoadingDialog;
import com.app.shopdodientu.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOutActivity extends AppCompatActivity {
    private TextView tvBack, tvUserName, tvPhone, tvAddress, tvAmount, tvTemporaryPrice, tvTotal, tvTotalBottom, tvCheckOut;
    private RecyclerView rcvProduct;
    private OrderAdapter checkoutAdapter;
    private List<CartItemModel> cartItemModels;
    private LinearLayout linearAddress;

    private static final int REQUEST_SELECT_ADDRESS = 1;
    private List<Integer> cartItemIds;
    String userAddress;

    private ActivityResultLauncher<Intent> addressSelectionLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        userAddress = data.getStringExtra("selected_address");
                        // Hiển thị địa chỉ đã chọn trên giao diện thanh toán
                        displaySelectedAddress(userAddress);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_check_out);

        MapItemView();
        renderView();


        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        linearAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckOutActivity.this, LocationActivity.class);
                addressSelectionLauncher.launch(intent);
            }
        });

    }

    private void displaySelectedAddress(String seletedAddress) {
        tvAddress.setText(seletedAddress);
    }


    private void MapItemView(){
        tvBack = findViewById(R.id.tvBack);
        tvUserName = findViewById(R.id.tvUserName);
        tvPhone = findViewById(R.id.tvPhone);
        tvAddress = findViewById(R.id.tvAddress);
        tvAmount = findViewById(R.id.tvAmount);
        tvTemporaryPrice = findViewById(R.id.tvTemporaryPrice);
        tvTotal = findViewById(R.id.tvTotal);
        tvTotalBottom = findViewById(R.id.tvTotalBottom);
        tvCheckOut = findViewById(R.id.tvCheckOut);
        rcvProduct = findViewById(R.id.rcvProduct);
        linearAddress = findViewById(R.id.linearAddress);
    }

    private void renderView() {
        SharedPreferences sharedPreferences = getSharedPreferences("address", MODE_PRIVATE);
        userAddress = sharedPreferences.getString("user_address", null);
        if(userAddress != null) {
            tvAddress.setText(userAddress);
        }
        UserModel userModel = Constant.userLogin;
        tvUserName.setText(userModel.getUsername());
        tvPhone.setText(userModel.getPhone());

        Intent intent = getIntent();
        List<CartItemModel> cartItems = (List<CartItemModel>) intent.getSerializableExtra("cartItemSelected");
        if(cartItems == null) {
            CartItemModel cartItemModel = (CartItemModel) intent.getSerializableExtra("cartItem");
            cartItems = new ArrayList<>();
            cartItems.add(cartItemModel);
            tvAmount.setText(String.valueOf(cartItemModel.getQuantity()));
            float totalPrice = cartItemModel.getUnitPrice()*cartItemModel.getQuantity();
            tvTemporaryPrice.setText("₫" + String.valueOf(totalPrice));
            tvTotal.setText( "₫" + String.valueOf(totalPrice));
            tvTotalBottom.setText("₫" + String.valueOf(totalPrice));
            tvCheckOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(userAddress != null){
                        LoadingDialog loadingDialog = new LoadingDialog(CheckOutActivity.this);
                        loadingDialog.show();
                        ApiService apiService = ApiClient.getApiService();
                        apiService.buyNow(cartItemModel.getProductId(), cartItemModel.getQuantity(), userAddress)
                                .enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        loadingDialog.dismiss();
                                        Intent intentOrder = new Intent(CheckOutActivity.this, MyOrderActivity.class);
                                        startActivity(intentOrder);
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        loadingDialog.dismiss();
                                        Toast.makeText(CheckOutActivity.this, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    else {
                        Toast.makeText(CheckOutActivity.this, "Vui lòng điền thông tin giao hàng", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            int amount = 0;
            float totalPrice = 0;
            cartItemIds = new ArrayList<>();
            for(CartItemModel ci : cartItems) {
                int quantity = ci.getQuantity();
                amount += quantity;
                cartItemIds.add(ci.getId());
                totalPrice += ci.getUnitPrice()*quantity;
            }
            tvAmount.setText(String.valueOf(amount));
            tvTemporaryPrice.setText("₫" + String.valueOf(totalPrice));
            tvTotal.setText( "₫" + String.valueOf(totalPrice));
            tvTotalBottom.setText("₫" + String.valueOf(totalPrice));
            tvCheckOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(userAddress != null){
                        LoadingDialog loadingDialog = new LoadingDialog(CheckOutActivity.this);
                        loadingDialog.show();
                        CheckoutModel checkoutModel = new CheckoutModel();
                        checkoutModel.setAddress(userAddress);
                        checkoutModel.setData(cartItemIds);
                        ApiService apiService = ApiClient.getApiService();
                        apiService.checkout(checkoutModel)
                                .enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        loadingDialog.dismiss();
                                        Intent intentOrder = new Intent(CheckOutActivity.this, MyOrderActivity.class);
                                        startActivity(intentOrder);
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        loadingDialog.dismiss();
                                        Toast.makeText(CheckOutActivity.this, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    else {
                        Toast.makeText(CheckOutActivity.this, "Vui lòng điền thông tin giao hàng", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        cartItemModels = cartItems;
        checkoutAdapter = new OrderAdapter(CheckOutActivity.this, cartItemModels);
        rcvProduct.setHasFixedSize(true);
        rcvProduct.setLayoutManager(new GridLayoutManager(CheckOutActivity.this, 1));
        rcvProduct.setAdapter(checkoutAdapter);
        checkoutAdapter.notifyDataSetChanged();
    }


}