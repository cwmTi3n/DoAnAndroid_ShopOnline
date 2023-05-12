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
    private int sellerId;

    public ShopPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, int sellerId) {
        super(fragmentManager, lifecycle);
        this.sellerId = sellerId;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeShopFragment(sellerId);
            case 1:
                return new ProductFragment();
            case 2:
                return new CatalogFragment();
            default:
                return new HomeShopFragment(sellerId);
        }
    }



    @Override
    public int getItemCount() {
        return 3;
    }
}
