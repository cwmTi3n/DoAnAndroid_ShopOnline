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
import com.app.shopdodientu.databinding.FragmentProvinceBinding;
import com.app.shopdodientu.databinding.FragmentWardBinding;

public class ProvinceFragment extends Fragment {

    private RecyclerView rcvProcince;
    private TextView tvNameProvince;

    FragmentProvinceBinding binding;
    public ProvinceFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = FragmentProvinceBinding.inflate(inflater, container, false);
        MapItemView();

        //vi tri load du lieu: recyclerview
        return binding.getRoot();
    }

    private void MapItemView(){
        rcvProcince = binding.getRoot().findViewById(R.id.rcvProvince);
        tvNameProvince = binding.getRoot().findViewById(R.id.name);
    }
}
