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
import com.app.shopdodientu.databinding.FragmentWaittingorderBinding;
import com.app.shopdodientu.databinding.FragmentWardBinding;

import java.util.Map;

public class WardFragment extends Fragment {

    private RecyclerView rcvWard;
    private TextView tvNameWard;
    FragmentWardBinding binding;
    public WardFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = FragmentWardBinding.inflate(inflater, container, false);
        MapItemView();
        //vi tri load du lieu: recyclerview
        return binding.getRoot();
    }
    private void MapItemView(){
        rcvWard = binding.getRoot().findViewById(R.id.rcvWard);
        tvNameWard = binding.getRoot().findViewById(R.id.name);
    }
}
