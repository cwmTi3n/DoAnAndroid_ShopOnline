package com.app.shopdodientu.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.shopdodientu.R;
import com.app.shopdodientu.databinding.ActivityMyOrderBinding;
import com.google.android.material.tabs.TabLayout;

public class MyOrderActivity extends AppCompatActivity {


    ActivityMyOrderBinding binding;
    ViewPager2Adapter viewPager2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

//    private void changeFabIcon(final int index){
//        binding.fabAction.hide();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                switch (index){
//                    case 0: binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_launcher_background));break;
//                    case 1: binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));break;
//                    case 2: binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));break;
//                }
//                binding.fabAction.show();
//            }
//        }, 2000);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
//        int id = item.getItemId();
//        switch (id){
//            case R.id.menuSearch: Toast.makeText(this, "Bạn đang chọn search",Toast.LENGTH_SHORT).show();break;
//            case R.id.menuNewGroup: Toast.makeText(this, "Bạn đang chọn more",Toast.LENGTH_SHORT).show();break;
//            case R.id.menuBroadcast: Toast.makeText(this, "Bạn đang chọn more",Toast.LENGTH_SHORT).show();break;
//            case R.id.menuWeb: Toast.makeText(this, "Bạn đang chọn more",Toast.LENGTH_SHORT).show();break;
//            case R.id.menuMessage: Toast.makeText(this, "Bạn đang chọn more",Toast.LENGTH_SHORT).show();break;
//            case R.id.menuSetting: Toast.makeText(this, "Bạn đang chọn Setting",Toast.LENGTH_SHORT).show();break;
//
//        }
        return super.onOptionsItemSelected(item);
    }
}