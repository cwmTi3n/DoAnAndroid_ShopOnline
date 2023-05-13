package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shopdodientu.R;
import com.app.shopdodientu.adapter.CartItemAdapter;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.CartItemModel;
import com.app.shopdodientu.util.LoadingDialog;
import com.app.shopdodientu.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    private ImageView imvDelete;
    private TextView tvBack, tvPriceTotal, tvCheckOut;
    private RecyclerView rcvProduct;
    private CheckBox checkBox;
    private CartItemAdapter cartItemAdapter;
    private List<CartItemModel> cartItemModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_cart);
        MapItemView();

        renderView();
        deleteItem();

    }

    private void MapItemView(){

        imvDelete = (ImageView) findViewById(R.id.imvDelete);
        tvBack = (TextView) findViewById(R.id.tvBack);
        tvPriceTotal = (TextView) findViewById(R.id.tvPriceTotal);
        tvCheckOut = (TextView) findViewById(R.id.tvCheckOut);
        rcvProduct = (RecyclerView) findViewById(R.id.rcvProduct);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
    }
    private void deleteItem() {
        imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog loadingDialog = new LoadingDialog(CartActivity.this);
                loadingDialog.show();
                List<Integer> itemSelect = cartItemAdapter.getCartItemSelect();
                if(!itemSelect.isEmpty()) {
                    ApiService apiService = ApiClient.getApiService();
                    apiService.deleteItemFromCart(itemSelect)
                            .enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    Toast.makeText(CartActivity.this, "Đã xóa sản phẩm khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
                                    loadingDialog.dismiss();
                                    recreate();
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Toast.makeText(CartActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                    loadingDialog.dismiss();
                                }
                            });
                }
                else {
                    loadingDialog.dismiss();
                }
            }
        });
    }

    private void renderView() {
        ApiService apiService = ApiClient.getApiService();
        apiService.getIntemsInCartNoCheckOut()
                .enqueue(new Callback<List<CartItemModel>>() {
                    @Override
                    public void onResponse(Call<List<CartItemModel>> call, Response<List<CartItemModel>> response) {
                        cartItemModels = response.body();
                        if(cartItemModels == null) {
                            cartItemModels = new ArrayList<>();
                        }
                        cartItemAdapter = new CartItemAdapter(getApplicationContext(), cartItemModels);
                        cartItemAdapter.setOnTotalAmountChangedListener(new CartItemAdapter.OnTotalAmountChangedListener() {
                            @Override
                            public void onTotalAmountChanged(double totalPrice) {
                                tvPriceTotal.setText(String.valueOf(totalPrice));
                            }
                        });
                        rcvProduct.setHasFixedSize(true);
                        rcvProduct.setLayoutManager(new GridLayoutManager(CartActivity.this, 1));
                        rcvProduct.setAdapter(cartItemAdapter);
                        cartItemAdapter.notifyDataSetChanged();
                        selectWithCheckBox();

                    }

                    @Override
                    public void onFailure(Call<List<CartItemModel>> call, Throwable t) {

                    }
                });
    }
    private void selectWithCheckBox() {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cartItemAdapter.setChecked(b);
                cartItemAdapter.notifyDataSetChanged();
            }
        });
    }

}