package com.app.shopdodientu.activity.AddressTabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.app.shopdodientu.activity.AddressTabLayout.MyPagerAdapter;
import com.app.shopdodientu.databinding.ActivityLocationBinding;
import com.google.android.material.tabs.TabLayout;

public class LocationActivity extends AppCompatActivity {

    ActivityLocationBinding binding;
    MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Province/City"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("District"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Ward"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        myPagerAdapter = new MyPagerAdapter(fragmentManager, getLifecycle());
        binding.viewPager2.setAdapter(myPagerAdapter);

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