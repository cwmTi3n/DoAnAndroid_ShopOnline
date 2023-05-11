package com.app.shopdodientu.activity.ShopTabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.app.shopdodientu.activity.ShopTabLayout.CatalogFragment;
import com.app.shopdodientu.activity.ShopTabLayout.HomeShopFragment;
import com.app.shopdodientu.activity.ShopTabLayout.ProductFragment;


public class ShopPagerAdapter extends FragmentStateAdapter {

    public ShopPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeShopFragment();
            case 1:
                return new ProductFragment();
            case 2:
                return new CatalogFragment();
            default:
                return new HomeShopFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
