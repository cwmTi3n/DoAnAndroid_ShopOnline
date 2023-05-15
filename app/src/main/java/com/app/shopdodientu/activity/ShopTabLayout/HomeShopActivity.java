package com.app.shopdodientu.activity.ShopTabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.ChatActivity;

import com.app.shopdodientu.databinding.ActivityHomeShopBinding;
import com.app.shopdodientu.util.UIHelper;
import com.google.android.material.tabs.TabLayout;

public class HomeShopActivity extends AppCompatActivity {

    ActivityHomeShopBinding binding;
    ShopPagerAdapter shopPagerAdapter;
    private LinearLayout  linearBannerShop;
    private TextView tvshopName, tvamountProduct, tvChat;
    private SearchView svProduct;
    private ImageView imgAvatarShop;
    private int sellerId;
    static String keyword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        sellerId = getIntent().getIntExtra("sellerId", 0);
        binding = ActivityHomeShopBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MapItemView();
//        renderView();


        SetTabLayout();
        TabLayOutClicked();
        searchProduct();
        gotoChat();
    }
    public static String getKeyword() {
        String tmp = keyword;
        keyword = "";
        return  tmp;
    }

    private void searchProduct() {
        svProduct.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                keyword = s;
                if(binding.viewPager2.getCurrentItem() == 1) {
                    ProductFragment productFragment = (ProductFragment) shopPagerAdapter.getFragment(1);
                    productFragment.updateKeyword();
                    productFragment.getProducts();
                }
                else {
                    binding.viewPager2.setCurrentItem(1);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
//                keyword = s;
//                binding.viewPager2.setCurrentItem(1);
//                return true;
                return false;
            }
        });

    }

    private void MapItemView(){
        linearBannerShop = findViewById(R.id.linearBannerShop);
        tvshopName = findViewById(R.id.tvshopName);
        tvamountProduct = findViewById(R.id.tvamountProduct);
        tvChat = findViewById(R.id.tvChat);
        svProduct = findViewById(R.id.svproduct);
        imgAvatarShop = findViewById(R.id.imgAvatarShop);
    }
    private void gotoChat(){
        tvChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeShopActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
    }

    private void SetTabLayout(){
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Home"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Product"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Catalog"));
        binding.tabLayout.setTabTextColors(Color.BLACK, Color.parseColor("#ee4d2d"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        shopPagerAdapter = new ShopPagerAdapter(fragmentManager, getLifecycle(), sellerId);
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


}