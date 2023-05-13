package com.app.shopdodientu.activity.seller.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.shopdodientu.R;
import com.app.shopdodientu.databinding.FragmentCheckingshoporderBinding;


public class CheckingShopOrderFragment extends Fragment {
    private TextView tvProductName, tvAmount, tvPrice, tvTotal, tvDetailOrder;
    private ImageView imgProduct;
    private Button btnCancel, btnDone;
    FragmentCheckingshoporderBinding binding;
    public CheckingShopOrderFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = FragmentCheckingshoporderBinding.inflate(inflater, container, false);

        MapItemView();
        //vi tri load du lieu: recyclerview
        return binding.getRoot();
    }

    private void MapItemView(){
        tvProductName = binding.getRoot().findViewById(R.id.tvProductName);
        tvAmount = binding.getRoot().findViewById(R.id.tvAmount);
        tvPrice = binding.getRoot().findViewById(R.id.tvPrice);
        tvTotal = binding.getRoot().findViewById(R.id.tvTotal);
        tvDetailOrder = binding.getRoot().findViewById(R.id.tvDetailOrder);
        imgProduct = binding.getRoot().findViewById(R.id.imgProduct);
        btnCancel = binding.getRoot().findViewById(R.id.btnCancel);
        btnDone = binding.getRoot().findViewById(R.id.btnDone);
    }
}
