package com.app.shopdodientu.activity.ShopTabLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.adapter.ProductHorizontalAdapter;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.databinding.FragmentHomeshopBinding;
import com.app.shopdodientu.model.PageModel;
import com.app.shopdodientu.model.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeShopFragment extends Fragment {
    private int page;
    private int total;
    private int sellerId;
    FragmentHomeshopBinding binding;
    private RecyclerView rcvLastProduct;
    private ProductHorizontalAdapter lastproductAdapter;
    private List<ProductModel> lastProducts;
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
        rcvLastProduct = binding.getRoot().findViewById(R.id.rcvLastProduct);
        //vi tri load du lieu: recyclerview
        //load more
        final NestedScrollView nestedScrollView = binding.getRoot().findViewById(R.id.nsvHomeShop);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    // At bottom of NestedScrollView, load more data for RecyclerView
                    loadMore();
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        renderView();
    }

    private void renderView() {
        renderProduct("desccreateDate");
    }

    private void renderProduct(String orderby) {
        ApiService apiService = ApiClient.getApiService();
        apiService.findProductsBySeller(sellerId, 0, "", 0, orderby)
                .enqueue(new Callback<PageModel<ProductModel>>() {
                    @Override
                    public void onResponse(Call<PageModel<ProductModel>> call, Response<PageModel<ProductModel>> response) {
                        PageModel<ProductModel> pageProducts = response.body();
                        if(pageProducts!= null) {
                            lastProducts = pageProducts.getContent();
                            total = pageProducts.getTotal();
                            page = pageProducts.getIndex();
                            lastproductAdapter = new ProductHorizontalAdapter(binding.getRoot().getContext(), lastProducts);
                            rcvLastProduct.setHasFixedSize(true);
                            rcvLastProduct.setLayoutManager(new GridLayoutManager(binding.getRoot().getContext(), 1));
                            rcvLastProduct.setAdapter(lastproductAdapter);
                            lastproductAdapter.notifyDataSetChanged();

                        }
                    }

                    @Override
                    public void onFailure(Call<PageModel<ProductModel>> call, Throwable t) {

                    }
                });
    }

    private void loadMore() {
        page = page + 1;
        if(page >= total) {
            return;
        }
        ApiService apiService = ApiClient.getApiService();
        apiService.findProductsBySeller(sellerId, 0, "", page, "desccreateDate")
                .enqueue(new Callback<PageModel<ProductModel>>() {
                    @Override
                    public void onResponse(Call<PageModel<ProductModel>> call, Response<PageModel<ProductModel>> response) {
                        PageModel<ProductModel> pageProduct = response.body();
                        assert pageProduct != null;
                        lastProducts.addAll(pageProduct.getContent());
                        lastproductAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<PageModel<ProductModel>> call, Throwable t) {

                    }
                });
    }


}
