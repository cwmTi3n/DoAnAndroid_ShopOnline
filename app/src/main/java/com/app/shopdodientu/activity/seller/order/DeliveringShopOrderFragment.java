package com.app.shopdodientu.activity.seller.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.app.shopdodientu.databinding.FragmentDeliveringshoporderBinding;
import com.app.shopdodientu.model.CartItemModel;
import com.app.shopdodientu.model.PageModel;
import com.app.shopdodientu.util.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DeliveringShopOrderFragment extends Fragment {
    private RecyclerView rcvDelivering;
    private List<CartItemModel> cartItemModels;
    private CartItemCheckAdapter cartItemCheckAdapter;
    private TextView tvProductName, tvAmount, tvPrice, tvTotal, tvDetailOrder;
    private ImageView imgProduct;
    FragmentDeliveringshoporderBinding binding;
    private int page = 0;
    private int total;
    public DeliveringShopOrderFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = FragmentDeliveringshoporderBinding.inflate(inflater, container, false);

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
        rcvDelivering = binding.getRoot().findViewById(R.id.rcvDeliveringOrder);
    }

    private void getCartItems() {
        page = 0;
        ApiService apiService = ApiClient.getApiService();
        apiService.getCartItemsBySeller(Constant.userLogin.getId(), page, 2)
                .enqueue(new Callback<PageModel<CartItemModel>>() {
                    @Override
                    public void onResponse(Call<PageModel<CartItemModel>> call, Response<PageModel<CartItemModel>> response) {
                        PageModel<CartItemModel> pageModel = response.body();
                        if(pageModel != null) {
                            page = pageModel.getIndex();
                            total = pageModel.getTotal();
                            cartItemModels = pageModel.getContent();
                            cartItemCheckAdapter = new CartItemCheckAdapter(getContext(), cartItemModels);
                            rcvDelivering.setHasFixedSize(true);
                            rcvDelivering.setLayoutManager(new GridLayoutManager(getContext(), 1));
                            rcvDelivering.setAdapter(cartItemCheckAdapter);
                            cartItemCheckAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<PageModel<CartItemModel>> call, Throwable t) {

                    }
                });
    }
}
