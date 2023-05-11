package com.app.shopdodientu.activity.AddressTabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.app.shopdodientu.activity.AddressTabLayout.DistrictFragment;
import com.app.shopdodientu.activity.AddressTabLayout.ProvinceFragment;
import com.app.shopdodientu.activity.AddressTabLayout.WardFragment;


public class MyPagerAdapter extends FragmentStateAdapter {

    public MyPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ProvinceFragment();
            case 1:
                return new DistrictFragment();
            case 2:
                return new WardFragment();
            default:
                return new ProvinceFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
