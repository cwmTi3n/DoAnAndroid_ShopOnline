package com.app.shopdodientu.activity.ShopTabLayout;

import android.util.SparseArray;

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
    private SparseArray<Fragment> fragmentArray = new SparseArray<>();

    public ShopPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, int sellerId) {
        super(fragmentManager, lifecycle);
        this.sellerId = sellerId;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                HomeShopFragment homeShopFragment = new HomeShopFragment(sellerId);
                fragmentArray.put(0, homeShopFragment);
                return homeShopFragment;
            case 1:
                ProductFragment productFragment = new ProductFragment(sellerId);
                fragmentArray.put(1, productFragment);
                return productFragment;
            case 2:
                CatalogFragment catalogFragment = new CatalogFragment();
                fragmentArray.put(2, catalogFragment);
                return catalogFragment;
            default:
                HomeShopFragment defaultFragment = new HomeShopFragment(sellerId);
                fragmentArray.put(0, defaultFragment);
                return defaultFragment;
        }
    }
    public Fragment getFragment(int position) {
        return fragmentArray.get(position);
    }


    @Override
    public int getItemCount() {
        return 3;
    }
}
