package com.app.shopdodientu.activity.OrderTabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Window;

import com.app.shopdodientu.activity.OrderTabLayout.ViewPager2Adapter;
import com.app.shopdodientu.databinding.ActivityMyOrderBinding;
import com.app.shopdodientu.util.UIHelper;
import com.google.android.material.tabs.TabLayout;

public class MyOrderActivity extends AppCompatActivity {


    ActivityMyOrderBinding binding;
    ViewPager2Adapter viewPager2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        binding = ActivityMyOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setSupportActionBar(binding.toolBar);

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Waitting"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Delivering"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Delivered"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Cancelled"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager2Adapter = new ViewPager2Adapter(fragmentManager, getLifecycle());
        binding.viewPager2.setAdapter(viewPager2Adapter);

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