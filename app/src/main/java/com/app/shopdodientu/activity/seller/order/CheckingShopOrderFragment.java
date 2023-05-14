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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.adapter.CartItemCheckAdapter;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.databinding.FragmentCheckingshoporderBinding;
import com.app.shopdodientu.model.CartItemModel;
import com.app.shopdodientu.model.PageModel;
import com.app.shopdodientu.util.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CheckingShopOrderFragment extends Fragment {
    private TextView tvProductName, tvAmount, tvPrice, tvTotal, tvDetailOrder;
    private ImageView imgProduct;
    private Button btnCancel, btnDone;
    private RecyclerView rcvChecking;
    private List<CartItemModel> cartItemModels;
    private CartItemCheckAdapter cartItemCheckAdapter;
    FragmentCheckingshoporderBinding binding;
    private int page;
    private int total;
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

    @Override
    public void onResume() {
        super.onResume();
        getCartItems();
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
        rcvChecking = binding.getRoot().findViewById(R.id.rcvWaittingOrder);
    }

    private void getCartItems() {
        page = 0;
        ApiService apiService = ApiClient.getApiService();
        apiService.getCartItemsBySeller(Constant.userLogin.getId(), page, 1)
                .enqueue(new Callback<PageModel<CartItemModel>>() {
                    @Override
                    public void onResponse(Call<PageModel<CartItemModel>> call, Response<PageModel<CartItemModel>> response) {
                        PageModel<CartItemModel> pageModel = response.body();
                        if(pageModel != null) {
                            page = pageModel.getIndex();
                            total = pageModel.getTotal();
                            cartItemModels = pageModel.getContent();
                            cartItemCheckAdapter = new CartItemCheckAdapter(getContext(), cartItemModels);
                            rcvChecking.setHasFixedSize(true);
                            rcvChecking.setLayoutManager(new GridLayoutManager(getContext(), 1));
                            rcvChecking.setAdapter(cartItemCheckAdapter);
                            cartItemCheckAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<PageModel<CartItemModel>> call, Throwable t) {

                    }
                });
    }
}
