package com.app.shopdodientu.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.adapter.CategoryAdapter;
import com.app.shopdodientu.adapter.ProductAdapter;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.CategoryModel;
import com.app.shopdodientu.model.PageModel;
import com.app.shopdodientu.model.ProductModel;
import com.app.shopdodientu.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvCategory;
    private ImageView imvProfile;
    private ImageView imvHome;
    private List<CategoryModel> categories;
    private CategoryAdapter categoryAdapter;
    private UserModel userModel;
    private RecyclerView rcvProduct;
    private ProductAdapter productAdapter;
    private List<ProductModel> products;
    private int page;
    private int total;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userModel = (UserModel) getIntent().getSerializableExtra("user");
        MapItemView();
        getAllCategory();
        getLastProduct();
        gotoProfile();
        gotoHome();
    }
    private void gotoProfile() {
        imvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userModel == null) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra("user", userModel);
                    startActivity(intent);
                }
            }
        });
    }

    private void gotoHome() {
        imvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });

    }


    private void MapItemView() {
        rcvCategory = (RecyclerView) findViewById(R.id.rcvcategory);
        imvProfile = (ImageView) findViewById(R.id.imgProfile);
        imvHome = (ImageView) findViewById(R.id.imgHome);
        rcvProduct = (RecyclerView) findViewById(R.id.rcvproduct);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
    }

    private void getAllCategory() {
        ApiService apiService = ApiClient.getApiService();
        apiService.getAllCategory()
                .enqueue(new Callback<List<CategoryModel>>() {
                    @Override
                    public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                        categories = response.body();
                        categoryAdapter = new CategoryAdapter(getApplicationContext(), categories);
                        rcvCategory.setHasFixedSize(true);
                        rcvCategory.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        rcvCategory.setAdapter(categoryAdapter);
                        categoryAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                        Log.d("mainactivity", "call get all category fail");
                    }
                });
    }

    private void getLastProduct() {
        ApiService apiService = ApiClient.getApiService();
        apiService.lastProduct(0)
                .enqueue(new Callback<PageModel<ProductModel>>() {
                    @Override
                    public void onResponse(Call<PageModel<ProductModel>> call, Response<PageModel<ProductModel>> response) {
                        PageModel<ProductModel> pageProduct = response.body();
                        assert pageProduct != null;
                        products = pageProduct.getContent();
                        page = pageProduct.getIndex();
                        total = pageProduct.getTotal();
                        productAdapter = new ProductAdapter(getApplicationContext(), products);
                        rcvProduct.setHasFixedSize(true);
                        rcvProduct.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
                        rcvProduct.setAdapter(productAdapter);
                        productAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<PageModel<ProductModel>> call, Throwable t) {

                    }
                });
        //load more
        RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // Kiểm tra xem RecyclerView có đang cuộn đến cuối cùng không
                if (!recyclerView.canScrollVertically(1)) {
                    // Gọi phương thức để tải thêm dữ liệu
                    loadmoreProduct();
                }
            }
        };
        rcvProduct.addOnScrollListener(onScrollListener);
        rcvProduct.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {
                int recyclerViewHeight = rcvProduct.getHeight();
                int recyclerViewItemCount = rcvProduct.getAdapter().getItemCount();
                int recyclerViewItemHeight = rcvProduct.getChildAt(0).getHeight();

                int scrollViewHeight = scrollView.getHeight();
                int scrollViewMaxScroll = recyclerViewItemHeight * recyclerViewItemCount - scrollViewHeight;

                if (scrollView.getScrollY() < scrollViewMaxScroll - recyclerViewItemHeight) {
                    scrollView.smoothScrollTo(0, scrollViewMaxScroll);
                }
            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {

            }
        });
    }
    private void loadmoreProduct() {
        ApiService apiService = ApiClient.getApiService();
        page = page + 1;
        if(page >= total) {
            return;
        }
        apiService.lastProduct(page)
                .enqueue(new Callback<PageModel<ProductModel>>() {
                    @Override
                    public void onResponse(Call<PageModel<ProductModel>> call, Response<PageModel<ProductModel>> response) {
                        PageModel<ProductModel> pageProduct = response.body();
                        assert pageProduct != null;
                        products.addAll(pageProduct.getContent());
                        productAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<PageModel<ProductModel>> call, Throwable t) {

                    }
                });
    }

}