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
import com.app.shopdodientu.databinding.FragmentWaittingorderBinding;
import com.app.shopdodientu.model.CartModel;
import com.app.shopdodientu.model.PageModel;
import com.app.shopdodientu.util.LoadingDialog;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WaittingOrderFragment extends Fragment {

    private RecyclerView rcvWaittingOrder;
    private CartAdapter cartAdapter;
    private List<CartModel> cartModels;
    private int page;
    private int total;
    private TextView tvDetailOrder, tvTotal, tvAmount, tvTimeOrder, tvChat;
    private ImageView imgProduct;
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

    @Override
    public void onResume() {
        super.onResume();
        getCarts();
    }

    private void MapItemView(){
        rcvWaittingOrder = binding.getRoot().findViewById(R.id.rcvWaittingOrder);
        tvDetailOrder = binding.getRoot().findViewById(R.id.tvDetailOrder);
        tvTotal = binding.getRoot().findViewById(R.id.tvTotal);
        tvTimeOrder = binding.getRoot().findViewById(R.id.tvTimeOrder);
        tvAmount = binding.getRoot().findViewById(R.id.tvAmount);
        imgProduct = binding.getRoot().findViewById(R.id.imgProduct);
        btnCancel = binding.getRoot().findViewById(R.id.btnCancel);
        tvChat = binding.getRoot().findViewById(R.id.tvChat);

    }

    private void getCarts() {
        LoadingDialog loadingDialog = new LoadingDialog(getContext());
        loadingDialog.show();
        page = 0;
        ApiService apiService = ApiClient.getApiService();
        apiService.getCarts(1, 0)
                .enqueue(new Callback<PageModel<CartModel>>() {
                    @Override
                    public void onResponse(Call<PageModel<CartModel>> call, Response<PageModel<CartModel>> response) {
                        PageModel<CartModel> pageModel = response.body();
                        if(pageModel != null) {
                            page = pageModel.getIndex();
                            total = pageModel.getTotal();
                            cartModels = pageModel.getContent();
                            cartAdapter = new CartAdapter(getContext(), cartModels);
                            rcvWaittingOrder.setHasFixedSize(true);
                            rcvWaittingOrder.setLayoutManager(new GridLayoutManager(getContext(), 1));
                            rcvWaittingOrder.setAdapter(cartAdapter);
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
