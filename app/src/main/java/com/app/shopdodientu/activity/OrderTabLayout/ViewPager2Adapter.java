package com.app.shopdodientu.activity.OrderTabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPager2Adapter extends FragmentStateAdapter {
    public ViewPager2Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new WaittingOrderFragment();
            case 1:
                return new DeliveringOrderFragment();
            case 2:
                return new DeliveredOrderFragment();
            case 3:
                return new CancelOrderFragment();
            default:
                return new WaittingOrderFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
