package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shopdodientu.R;
import com.app.shopdodientu.adapter.CartItemAdapter;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.CartItemModel;
import com.app.shopdodientu.util.UIHelper;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    private ImageView imvDeleteAll;
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
        DeleteAllClicked();
        renderView();

    }

    private void MapItemView(){

        imvDeleteAll = (ImageView) findViewById(R.id.imvDeleteAll);
        tvBack = (TextView) findViewById(R.id.tvBack);
        tvPriceTotal = (TextView) findViewById(R.id.tvPriceTotal);
        tvCheckOut = (TextView) findViewById(R.id.tvCheckOut);
        rcvProduct = (RecyclerView) findViewById(R.id.rcvProduct);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
    }

    private void renderView() {
        ApiService apiService = ApiClient.getApiService();
        apiService.getIntemsInCartNoCheckOut()
                .enqueue(new Callback<List<CartItemModel>>() {
                    @Override
                    public void onResponse(Call<List<CartItemModel>> call, Response<List<CartItemModel>> response) {
                        cartItemModels = response.body();
                        if(cartItemModels != null) {
                            Log.d("size", String.valueOf(cartItemModels.size()));
                            cartItemAdapter = new CartItemAdapter(getApplicationContext(), cartItemModels);
                            rcvProduct.setHasFixedSize(true);
                            rcvProduct.setLayoutManager(new GridLayoutManager(CartActivity.this, 1));
                            rcvProduct.setAdapter(cartItemAdapter);
                            cartItemAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<CartItemModel>> call, Throwable t) {

                    }
                });
    }

    private void DeleteAllClicked() {
        imvDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(CartActivity.this, imvDeleteAll);
                popupMenu.getMenuInflater().inflate(R.menu.menu_delete_cart, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete:
                                Toast.makeText(CartActivity.this, "Deleted All", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                // Hiển thị PopupMenu
                popupMenu.show();
            }

        });
    }
}