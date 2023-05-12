package com.app.shopdodientu.activity.ShopTabLayout;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.SearchActivity;
import com.app.shopdodientu.adapter.ProductAdapter;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.databinding.FragmentProductBinding;
import com.app.shopdodientu.model.PageModel;
import com.app.shopdodientu.model.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFragment extends Fragment {

    FragmentProductBinding binding;
    TextView tvRelated, lineRelated, tvLatest, lineLatest, tvBest, lineBest, tvPrice;
    TextView currentTextView, currentLine;
    private RecyclerView rcvProduct;
    private int total;
    private int page;
    private int sellerId;
    private String orderby = null;
    private ProductAdapter productAdapter;
    private List<ProductModel> products;

    public ProductFragment(int sellerId){
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
        binding = FragmentProductBinding.inflate(inflater, container, false);

        MapItemView();
        currentTextView = tvRelated;
        currentLine = lineRelated;
        TextViewRelatedClicked();
        TextViewLatestClicked();
        TextViewBestClicked();
        TextViewPriceClicked();

        //vi tri load du lieu: recyclerview
        getProducts();
        //load more
        final NestedScrollView nestedScrollView = binding.getRoot().findViewById(R.id.nsvProduct);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    // At bottom of NestedScrollView, load more data for RecyclerView
                    loadmoreProduct();
                }
            }
        });
        return binding.getRoot();
    }

    private void MapItemView(){
        tvRelated = binding.getRoot().findViewById(R.id.tvRelated);
        lineRelated = binding.getRoot().findViewById(R.id.lineRelated);
        tvLatest = binding.getRoot().findViewById(R.id.tvLatest);
        lineLatest  = binding.getRoot().findViewById(R.id.lineLatest);
        tvBest = binding.getRoot().findViewById(R.id.tvBest);
        lineBest = binding.getRoot().findViewById(R.id.lineBest);
        tvPrice = binding.getRoot().findViewById(R.id.tvPrice);
        rcvProduct = binding.getRoot().findViewById(R.id.rcvProduct);
    }

    private void TextViewRelatedClicked(){
        tvRelated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentTextView != tvRelated){
                    SetEffectLastTextViewTop(currentTextView);
                    SetEffectLineLastTextViewTop(currentLine);
                }
                if(currentTextView == tvPrice){
                    SetDrawablePriceMove();
                }
                SetEffectCurrentTextViewTop(tvRelated);
                SetEffectLineCurrentTextViewTop(lineRelated);
                currentTextView = tvRelated;
                currentLine = lineRelated;
                orderby = null;
                getProducts();
            }
        });
    }

    private void TextViewLatestClicked(){
        tvLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentTextView != tvLatest){
                    SetEffectLastTextViewTop(currentTextView);
                    SetEffectLineLastTextViewTop(currentLine);
                }
                if(currentTextView == tvPrice){
                    SetDrawablePriceMove();
                }
                SetEffectCurrentTextViewTop(tvLatest);
                SetEffectLineCurrentTextViewTop(lineLatest);
                currentTextView = tvLatest;
                currentLine = lineLatest;
                orderby = "desccreateDate";
                getProducts();
            }
        });
    }

    private void TextViewBestClicked(){
        tvBest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentTextView != tvBest){
                    SetEffectLastTextViewTop(currentTextView);
                    SetEffectLineLastTextViewTop(currentLine);
                }
                if(currentTextView == tvPrice){
                    SetDrawablePriceMove();
                }
                SetEffectCurrentTextViewTop(tvBest);
                SetEffectLineCurrentTextViewTop(lineBest);
                currentTextView = tvBest;
                currentLine = lineBest;
                orderby = "descamount";
                getProducts();
            }
        });
    }

    private void TextViewPriceClicked(){
        tvPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentTextView != null) {
                    SetEffectLastTextViewTop(currentTextView);
                    SetEffectLineLastTextViewTop(currentLine);
                }
                SetDrawablePriceClicked();
                SetEffectCurrentTextViewTop(tvPrice);
                currentTextView = tvPrice;
            }
        });
    }

    private void SetEffectLastTextViewTop(TextView last){
        last.setTypeface(null, Typeface.NORMAL);
        last.setTextColor(Color.parseColor("#8B7C7C"));
    }
    private void SetEffectCurrentTextViewTop(TextView current){
        current.setTypeface(null, Typeface.BOLD);
        current.setTextColor(Color.parseColor("#FFA500"));
    }
    private void SetEffectLineCurrentTextViewTop(TextView line){
        line.setBackgroundColor(Color.parseColor("#FFA500"));
    }
    private void SetEffectLineLastTextViewTop(TextView line) {
        line.setBackgroundColor(Color.parseColor("#F1E6E6"));
    }

    private void SetDrawablePriceClicked(){
        Drawable[] drawables = tvPrice.getCompoundDrawables();
        Drawable rightDrawable = drawables[2];
        Drawable myDrawable = getResources().getDrawable(R.drawable.unfold);
        Drawable drawableUpArrow = getResources().getDrawable(R.drawable.up_arrow);
        Drawable drawableDownArrow = getResources().getDrawable(R.drawable.down_arrow);


        if (rightDrawable != null && myDrawable != null && rightDrawable.getConstantState().equals(myDrawable.getConstantState())) {
            tvPrice.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawableUpArrow, null);
            orderby = "price";
            getProducts();

        } else if (rightDrawable != null && drawableUpArrow != null && rightDrawable.getConstantState().equals(drawableUpArrow.getConstantState())){
            tvPrice.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawableDownArrow, null);
            orderby = "descprice";
            getProducts();

        } else if (rightDrawable != null && drawableDownArrow != null && rightDrawable.getConstantState().equals(drawableDownArrow.getConstantState())){
            tvPrice.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawableUpArrow, null);
            orderby = "price";
            getProducts();
        }
    }
    private void SetDrawablePriceMove(){
        Drawable drawableRight = getResources().getDrawable(R.drawable.unfold);
        tvPrice.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawableRight , null);
    }

    private void getProducts() {
        page = 0;
        ApiService apiService = ApiClient.getApiService();
        apiService.findProductsBySeller(sellerId, page, orderby)
                .enqueue(new Callback<PageModel<ProductModel>>() {
                    @Override
                    public void onResponse(Call<PageModel<ProductModel>> call, Response<PageModel<ProductModel>> response) {
                        PageModel<ProductModel> pageProduct = response.body();
                        if(pageProduct != null) {
                            total = pageProduct.getTotal();
                            page = pageProduct.getIndex();
                            products = pageProduct.getContent();
                            productAdapter = new ProductAdapter(getContext(), products);
                            rcvProduct.setHasFixedSize(true);
                            rcvProduct.setLayoutManager(new GridLayoutManager(getContext(), 2));
                            rcvProduct.setAdapter(productAdapter);
                            productAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<PageModel<ProductModel>> call, Throwable t) {

                    }
                });
    }
    private void loadmoreProduct() {
        page = page + 1;
        if(page >= total) {
            return;
        }
        ApiService apiService = ApiClient.getApiService();
        apiService.findProductsBySeller(sellerId, page, orderby)
                .enqueue(new Callback<PageModel<ProductModel>>() {
                    @Override
                    public void onResponse(Call<PageModel<ProductModel>> call, Response<PageModel<ProductModel>> response) {
                        PageModel<ProductModel> pageProduct = response.body();
                        if(pageProduct != null) {
                            products.addAll(pageProduct.getContent());
                            productAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<PageModel<ProductModel>> call, Throwable t) {

                    }
                });
    }
}
