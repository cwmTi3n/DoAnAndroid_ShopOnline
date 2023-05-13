package com.app.shopdodientu.activity.seller.order;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class ShopOrderPagerAdapter extends FragmentStateAdapter {
    public ShopOrderPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new CheckingShopOrderFragment();
            case 1:
                return new DeliveringShopOrderFragment();
            case 2:
                return new DeliveredShopOrderFragment();
            case 3:
                return new CancelShopOrderFragment();
            default:
                return new CheckingShopOrderFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
