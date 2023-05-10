package com.app.shopdodientu.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.shopdodientu.databinding.FragmentDeliveringorderBinding;


public class DeliveringOrderFragment extends Fragment {
    FragmentDeliveringorderBinding binding;
    public DeliveringOrderFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = FragmentDeliveringorderBinding.inflate(inflater, container, false);
        //vi tri load du lieu: recyclerview
        return binding.getRoot();
    }
}
