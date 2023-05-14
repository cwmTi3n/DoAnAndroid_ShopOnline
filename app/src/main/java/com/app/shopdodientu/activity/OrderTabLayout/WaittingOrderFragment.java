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
import com.app.shopdodientu.databinding.FragmentWaittingorderBinding;

import java.util.Map;


public class WaittingOrderFragment extends Fragment {

    private RecyclerView rcvWaittingOrder;
    private TextView tvDetailOrder, tvTotal, tvAmountProduct, tvPrice, tvAmount, tvProductName, tvShopName, tvChat;
    private Button btnCancel;
    FragmentWaittingorderBinding binding;
    public WaittingOrderFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = FragmentWaittingorderBinding.inflate(inflater, container, false);
        MapItemView();

        //vi tri load du lieu: recyclerview
        return binding.getRoot();
    }

    private void MapItemView(){
        rcvWaittingOrder = binding.getRoot().findViewById(R.id.rcvWaittingOrder);
        tvDetailOrder = binding.getRoot().findViewById(R.id.tvDetailOrder);
        tvTotal = binding.getRoot().findViewById(R.id.tvTotal);
        tvAmountProduct = binding.getRoot().findViewById(R.id.tvAmountProduct);
        tvPrice = binding.getRoot().findViewById(R.id.tvPrice);
        tvAmount = binding.getRoot().findViewById(R.id.tvAmount);
        tvProductName = binding.getRoot().findViewById(R.id.tvProductName);
        tvShopName = binding.getRoot().findViewById(R.id.tvShopName);
        btnCancel = binding.getRoot().findViewById(R.id.btnCancel);
        tvChat = binding.getRoot().findViewById(R.id.tvChat);

    }
}
