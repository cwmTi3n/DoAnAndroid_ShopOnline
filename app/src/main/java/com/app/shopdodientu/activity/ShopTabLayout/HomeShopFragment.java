package com.app.shopdodientu.activity.ShopTabLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.shopdodientu.databinding.FragmentHomeshopBinding;
import com.app.shopdodientu.databinding.FragmentWardBinding;

public class HomeShopFragment extends Fragment {
    FragmentHomeshopBinding binding;
    public HomeShopFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = FragmentHomeshopBinding.inflate(inflater, container, false);

        //vi tri load du lieu: recyclerview
        return binding.getRoot();
    }
}
