package com.app.shopdodientu.activity.OrderTabLayout;

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
import com.app.shopdodientu.adapter.CartAdapter;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.databinding.FragmentDeliveredorderBinding;
import com.app.shopdodientu.model.CartModel;
import com.app.shopdodientu.model.PageModel;
import com.app.shopdodientu.util.LoadingDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DeliveredOrderFragment extends Fragment {

    private RecyclerView rcvDeliveredOrder;
    private CartAdapter cartAdapter;
    private List<CartModel> cartModels;
    private int page;
    private int total;
    private TextView tvDetailOrder, tvTotal, tvAmount, tvTimeOrder;
    private Button btnRepurchase;
    private ImageView imgProduct;
    FragmentDeliveredorderBinding binding;
    public DeliveredOrderFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getCarts();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = FragmentDeliveredorderBinding.inflate(inflater, container, false);
        MapItemView();
        //vi tri load du lieu: recyclerview
        return binding.getRoot();
    }

    private void MapItemView(){
        rcvDeliveredOrder = binding.getRoot().findViewById(R.id.rcvDeliveredOrder);
        tvDetailOrder = binding.getRoot().findViewById(R.id.tvDetailOrder);
        tvTotal = binding.getRoot().findViewById(R.id.tvTotal);
        tvTimeOrder = binding.getRoot().findViewById(R.id.tvTimeOrder);
        tvAmount = binding.getRoot().findViewById(R.id.tvAmount);
        btnRepurchase = binding.getRoot().findViewById(R.id.btnRepurchase);
        imgProduct = binding.getRoot().findViewById(R.id.imgProduct);

    }

    private void getCarts() {
        LoadingDialog loadingDialog = new LoadingDialog(getContext());
        loadingDialog.show();
        page = 0;
        ApiService apiService = ApiClient.getApiService();
        apiService.getCarts(3, page)
                .enqueue(new Callback<PageModel<CartModel>>() {
                    @Override
                    public void onResponse(Call<PageModel<CartModel>> call, Response<PageModel<CartModel>> response) {
                        PageModel<CartModel> pageModel = response.body();
                        if(pageModel != null) {
                            page = pageModel.getIndex();
                            total = pageModel.getTotal();
                            cartModels = pageModel.getContent();
                            cartAdapter = new CartAdapter(getContext(), cartModels);
                            rcvDeliveredOrder.setHasFixedSize(true);
                            rcvDeliveredOrder.setLayoutManager(new GridLayoutManager(getContext(), 1));
                            rcvDeliveredOrder.setAdapter(cartAdapter);
                            cartAdapter.notifyDataSetChanged();
                        }
                        loadingDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<PageModel<CartModel>> call, Throwable t) {
                        loadingDialog.dismiss();
                    }
                });
    }
}
