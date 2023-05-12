package com.app.shopdodientu.activity.ShopTabLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.adapter.ProductAdapter;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.databinding.FragmentHomeshopBinding;
import com.app.shopdodientu.databinding.FragmentWardBinding;
import com.app.shopdodientu.model.PageModel;
import com.app.shopdodientu.model.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeShopFragment extends Fragment {
    private int sellerId;
    FragmentHomeshopBinding binding;
    private RecyclerView rcvRecommend, rcvLastProduct;
    private ProductAdapter recommendProductAdapter, lastproductAdapter;
    private List<ProductModel> recommendProducts, lastProducts;
    public HomeShopFragment(int sellerId){
        this.sellerId = sellerId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = FragmentHomeshopBinding.inflate(inflater, container, false);
        rcvRecommend = binding.getRoot().findViewById(R.id.rcvRecommend);
        rcvLastProduct = binding.getRoot().findViewById(R.id.rcvLastProduct);
        //vi tri load du lieu: recyclerview
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        renderView();
    }

    private void renderView() {

        renderProduct("descamount");
        renderProduct("desccreateDate");
    }

    private void renderProduct(String orderby) {
        ApiService apiService = ApiClient.getApiService();
        apiService.findProductsBySeller(sellerId, 0, orderby)
                .enqueue(new Callback<PageModel<ProductModel>>() {
                    @Override
                    public void onResponse(Call<PageModel<ProductModel>> call, Response<PageModel<ProductModel>> response) {
                        PageModel<ProductModel> page = response.body();
                        if(page!= null) {
                            if(orderby.equals("descamount")) {
                                recommendProducts = page.getContent();
                                recommendProductAdapter = new ProductAdapter(binding.getRoot().getContext(), recommendProducts);
                                rcvRecommend.setHasFixedSize(true);
                                rcvRecommend.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext(), LinearLayoutManager.HORIZONTAL, false));
                                rcvRecommend.setAdapter(recommendProductAdapter);
                                recommendProductAdapter.notifyDataSetChanged();
                            }
                            else if(orderby.equals("desccreateDate")) {
                                lastProducts = page.getContent();
                                lastproductAdapter = new ProductAdapter(binding.getRoot().getContext(), lastProducts);
                                rcvLastProduct.setHasFixedSize(true);
                                rcvLastProduct.setLayoutManager(new GridLayoutManager(binding.getRoot().getContext(), 2));
                                rcvLastProduct.setAdapter(lastproductAdapter);
                                lastproductAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PageModel<ProductModel>> call, Throwable t) {

                    }
                });
    }


}
