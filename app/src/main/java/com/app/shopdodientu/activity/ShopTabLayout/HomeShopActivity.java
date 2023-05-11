package com.app.shopdodientu.activity.ShopTabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.databinding.ActivityHomeShopBinding;
import com.google.android.material.tabs.TabLayout;

public class HomeShopActivity extends AppCompatActivity {

    ActivityHomeShopBinding binding;
    ShopPagerAdapter shopPagerAdapter;
    private LinearLayout  linearBannerShop;
    private TextView tvshopName, tvamountProduct, tvChat, tvSearch;
    private ImageView imgAvatarShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeShopBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MapItemView();
//        renderView();


        SetTabLayout();
        TabLayOutClicked();

    }

    private void MapItemView(){
        linearBannerShop = findViewById(R.id.linearBannerShop);
        tvshopName = findViewById(R.id.tvshopName);
        tvamountProduct = findViewById(R.id.tvamountProduct);
        tvChat = findViewById(R.id.tvChat);
        tvSearch = findViewById(R.id.tvSearch);
        imgAvatarShop = findViewById(R.id.imgAvatarShop);
    }

    private void SetTabLayout(){
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Home"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Product"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Catalog"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        shopPagerAdapter = new ShopPagerAdapter(fragmentManager, getLifecycle());
        binding.viewPager2.setAdapter(shopPagerAdapter);
    }

    private void TabLayOutClicked(){
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager2.setCurrentItem(tab.getPosition());
//                changeFabIcon(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position));
            }
        });
    }

    private void renderView() {

//        renderProduct("descamount");
//        renderProduct("desccreateDate");
    }

//    private void renderProduct(String orderby) {
//        ApiService apiService = ApiClient.getApiService();
//        int sellerId = getIntent().getIntExtra("sellerId", 0);
//        apiService.findProductsBySeller(sellerId, 0, orderby)
//                .enqueue(new Callback<PageModel<ProductModel>>() {
//                    @Override
//                    public void onResponse(Call<PageModel<ProductModel>> call, Response<PageModel<ProductModel>> response) {
//                        PageModel<ProductModel> page = response.body();
//                        if(page!= null) {
//                            if(orderby.equals("descamount")) {
//                                recommendProducts = page.getContent();
//                                recommendProductAdapter = new ProductAdapter(getApplicationContext(), recommendProducts);
//                                rcvRecommend.setHasFixedSize(true);
//                                rcvRecommend.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//                                rcvRecommend.setAdapter(recommendProductAdapter);
//                                recommendProductAdapter.notifyDataSetChanged();
//                            }
//                            else if(orderby.equals("desccreateDate")) {
//                                lastProducts = page.getContent();
//                                lastproductAdapter = new ProductAdapter(getApplicationContext(), lastProducts);
//                                rcvLastProduct.setHasFixedSize(true);
//                                rcvLastProduct.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
//                                rcvLastProduct.setAdapter(lastproductAdapter);
//                                lastproductAdapter.notifyDataSetChanged();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<PageModel<ProductModel>> call, Throwable t) {
//
//                    }
//                });
//    }

}