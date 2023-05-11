package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.adapter.CategoryAdapter;
import com.app.shopdodientu.adapter.ProductAdapter;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.PageModel;
import com.app.shopdodientu.model.ProductModel;
import com.app.shopdodientu.util.UIHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeShopActivity extends AppCompatActivity {

    private LinearLayout linearShop, linearProduct, linearCatalog, linearBannerShop;
    private TextView tvshopName, tvamountProduct, tvChat, tvSearch;
    private ImageView imgAvatarShop;
    private RecyclerView rcvLastProduct, rcvRecommend;
    private List<ProductModel> lastProducts, recommendProducts;
    private ProductAdapter lastproductAdapter, recommendProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_shop);

        MapItemView();
        renderView();
        UIHelper.gotoProductShop(linearProduct, this);
        UIHelper.gotoCatalogShop(linearCatalog, this);
    }

    private void MapItemView(){
        linearShop = (LinearLayout) findViewById(R.id.linearShop);
        linearProduct = (LinearLayout) findViewById(R.id.linearProduct);
        linearCatalog = (LinearLayout) findViewById(R.id.linearCatalog);
        linearBannerShop = findViewById(R.id.linearBannerShop);
        tvshopName = findViewById(R.id.tvshopName);
        tvamountProduct = findViewById(R.id.tvamountProduct);
        tvChat = findViewById(R.id.tvChat);
        tvSearch = findViewById(R.id.tvSearch);
        imgAvatarShop = findViewById(R.id.imgAvatarShop);
        rcvRecommend = findViewById(R.id.rcvRecommend);
        rcvLastProduct = findViewById(R.id.rcvLastProduct);
    }

    private void renderView() {

        renderProduct("descamount");
        renderProduct("desccreateDate");
    }

    private void renderProduct(String orderby) {
        ApiService apiService = ApiClient.getApiService();
        int sellerId = getIntent().getIntExtra("sellerId", 0);
        apiService.findProductsBySeller(sellerId, 0, orderby)
                .enqueue(new Callback<PageModel<ProductModel>>() {
                    @Override
                    public void onResponse(Call<PageModel<ProductModel>> call, Response<PageModel<ProductModel>> response) {
                        PageModel<ProductModel> page = response.body();
                        if(page!= null) {
                            if(orderby.equals("descamount")) {
                                recommendProducts = page.getContent();
                                recommendProductAdapter = new ProductAdapter(getApplicationContext(), recommendProducts);
                                rcvRecommend.setHasFixedSize(true);
                                rcvRecommend.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                                rcvRecommend.setAdapter(recommendProductAdapter);
                                recommendProductAdapter.notifyDataSetChanged();
                            }
                            else if(orderby.equals("desccreateDate")) {
                                lastProducts = page.getContent();
                                lastproductAdapter = new ProductAdapter(getApplicationContext(), lastProducts);
                                rcvLastProduct.setHasFixedSize(true);
                                rcvLastProduct.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                                rcvLastProduct.setAdapter(lastproductAdapter);
                                lastproductAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PageModel<ProductModel>> call, Throwable t) {

                    }
                });
    }




}