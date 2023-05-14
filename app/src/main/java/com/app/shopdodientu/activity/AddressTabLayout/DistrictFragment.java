package com.app.shopdodientu.activity.AddressTabLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.databinding.FragmentDistrictBinding;
import com.app.shopdodientu.databinding.FragmentProvinceBinding;

public class DistrictFragment extends Fragment {
    private RecyclerView rcvDistrict;
    private TextView tvNameDistrict;

    FragmentDistrictBinding binding;
    public DistrictFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = FragmentDistrictBinding.inflate(inflater, container, false);
        MapItemView();

        //vi tri load du lieu: recyclerview
        return binding.getRoot();
    }

    private void MapItemView(){
        rcvDistrict = binding.getRoot().findViewById(R.id.rcvDistrict);
        tvNameDistrict = binding.getRoot().findViewById(R.id.name);
    }
}
