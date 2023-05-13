package com.app.shopdodientu.activity.ShopTabLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.MainActivity;
import com.app.shopdodientu.adapter.CategoryAdapter;
import com.app.shopdodientu.adapter.CategoryHorizontalAdapter;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.databinding.FragmentCatalogBinding;
import com.app.shopdodientu.databinding.FragmentProductBinding;
import com.app.shopdodientu.model.CategoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogFragment extends Fragment implements CategoryHorizontalAdapter.OnCategoryClickListener{

    FragmentCatalogBinding binding;
    private RecyclerView rcvCategory;
    private List<CategoryModel> categories;
    private CategoryHorizontalAdapter categoryHorizontalAdapter;
    private ViewPager2 viewPager;
    private static int categoryId;
    public CatalogFragment(){}
    static int getCategoryId(){
        int tmp = categoryId;
        categoryId = 0;
        return tmp;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = FragmentCatalogBinding.inflate(inflater, container, false);
        rcvCategory = binding.getRoot().findViewById(R.id.rcvCategory);
        viewPager = getActivity().findViewById(R.id.viewPager2);
        //vi tri load du lieu: recyclerview
        getAllCategory();
        return binding.getRoot();
    }

    private void getAllCategory() {
        ApiService apiService = ApiClient.getApiService();
        apiService.getAllCategory()
                .enqueue(new Callback<List<CategoryModel>>() {
                    @Override
                    public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                        categories = response.body();
                        if(categories != null) {
                            categoryHorizontalAdapter = new CategoryHorizontalAdapter(getContext(), categories);
                            rcvCategory.setHasFixedSize(true);
                            rcvCategory.setLayoutManager(new GridLayoutManager(binding.getRoot().getContext(), 1));
                            rcvCategory.setAdapter(categoryHorizontalAdapter);
                            categoryHorizontalAdapter.setOnCategoryClickListener(CatalogFragment.this);
                            categoryHorizontalAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                        Log.d("mainactivity", "call get all category fail");
                    }
                });
    }

    @Override
    public void onCategoryClick(CategoryModel category) {
        categoryId = category.getId();
        viewPager.setCurrentItem(1);
    }
}
