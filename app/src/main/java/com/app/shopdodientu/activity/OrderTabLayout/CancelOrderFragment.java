package com.app.shopdodientu.activity.OrderTabLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.databinding.FragmentCancelorderBinding;


public class CancelOrderFragment extends Fragment {
    private RecyclerView rcvCancelOrder;
    private TextView tvDetailOrder, tvTotal, tvAmountProduct, tvPrice, tvAmount, tvProductName, tvShopName;
    private Button btnRepurchase;
    FragmentCancelorderBinding binding;
    public CancelOrderFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = FragmentCancelorderBinding.inflate(inflater, container, false);
        MapItemView();
        //vi tri load du lieu: recyclerview
        return binding.getRoot();
    }

    private void MapItemView(){
        rcvCancelOrder = binding.getRoot().findViewById(R.id.rcvCancelOrder);
        tvDetailOrder = binding.getRoot().findViewById(R.id.tvDetailOrder);
        tvTotal = binding.getRoot().findViewById(R.id.tvTotal);
        tvAmountProduct = binding.getRoot().findViewById(R.id.tvAmountProduct);
        tvPrice = binding.getRoot().findViewById(R.id.tvPrice);
        tvAmount = binding.getRoot().findViewById(R.id.tvAmount);
        tvProductName = binding.getRoot().findViewById(R.id.tvProductName);
        tvShopName = binding.getRoot().findViewById(R.id.tvShopName);
        btnRepurchase = binding.getRoot().findViewById(R.id.btnRepurchase);

    }
}
