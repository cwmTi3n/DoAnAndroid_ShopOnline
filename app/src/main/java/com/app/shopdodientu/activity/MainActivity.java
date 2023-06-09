package com.app.shopdodientu.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.app.shopdodientu.R;
import com.app.shopdodientu.adapter.CategoryAdapter;
import com.app.shopdodientu.adapter.ImagesViewPageAdapter;
import com.app.shopdodientu.adapter.MenuCategoryAdapter;
import com.app.shopdodientu.adapter.ProductAdapter;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.CategoryModel;
import com.app.shopdodientu.model.ImageModel;
import com.app.shopdodientu.model.ItemMenuModel;
import com.app.shopdodientu.model.PageModel;
import com.app.shopdodientu.model.ProductModel;
import com.app.shopdodientu.model.UserModel;
import com.app.shopdodientu.util.Constant;
import com.app.shopdodientu.util.LoadingDialog;
import com.app.shopdodientu.util.UIHelper;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //SliderImage
    private ViewPager viewPager;
    private List<ImageModel> imagesList;
    private ActivityResultLauncher<Intent> gotoAccount = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        // Hiển thị địa chỉ đã chọn trên giao diện thanh toán
                        if (Constant.userLogin != null) {
                            Intent intent = new Intent(MainActivity.this, MyAccountActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> gotoCart = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        // Hiển thị địa chỉ đã chọn trên giao diện thanh toán
                        if (Constant.userLogin != null) {
                            Intent intent = new Intent(MainActivity.this, CartActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
    );

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager.getCurrentItem()==imagesList.size()-1)
            {
                viewPager.setCurrentItem(0);
            }
            else {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        }
    };

    //Menu
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ListView listView;
    ArrayList<ItemMenuModel> arrayList;
    MenuCategoryAdapter adapter;

    //BOTTOM
    private ImageView imvHome, imvAccount, imvLogout;
    private TextView tvLogout;
    private LinearLayout linearHome, linearAccount, linearCart, linearSupport, linearLogout, linearLogin;


    private RecyclerView rcvCategory;
    private List<CategoryModel> categories;
    private CategoryAdapter categoryAdapter;
    private RecyclerView rcvProduct;
    private ProductAdapter productAdapter;
    private List<ProductModel> products;

    private SearchView svProduct;
    private int page;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_main);
        MapItemView();
        SliderImage();
//        ActionToolBar();
//        MapListViewMenu();
        getAllCategory();

        if(Constant.userLogin != null)
        {
            tvLogout.setText("Logout");
            imvLogout.setImageDrawable(null);
            imvLogout.setBackgroundResource(R.drawable.bottom_btn5);
        }
        getLastProduct();
        UIHelper.gotoAccount(linearAccount, getApplicationContext(), gotoAccount);
        UIHelper.gotoCart(linearCart, this, gotoCart);
        gotoSupport();
        gotoHome(this);
        searchProduct();
        renderView();

        //load more
        final NestedScrollView nestedScrollView = findViewById(R.id.ncvMain);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    // At bottom of NestedScrollView, load more data for RecyclerView
                    loadmoreProduct();
                }
            }
        });

    }

    private void renderView() {
        if(Constant.userLogin != null) {
            linearLogout.setVisibility(View.VISIBLE);
            linearLogin.setVisibility(View.GONE);
            UIHelper.logout(linearLogout, this);
        }
        else {
            UIHelper.gotoAccount(linearLogin, MainActivity.this, gotoAccount);
        }
    }
    private void gotoHome(Activity activity) {
        linearHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
                activity.startActivity(activity.getIntent());
            }
        });
    }
    private void gotoSupport(){
        linearSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SupportActivity.class);
                startActivity(intent);
            }
        });
    }


    private void MapItemView() {
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        navigationView = (NavigationView) findViewById(R.id.navigationView);
//        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
//        listView = (ListView) findViewById(R.id.lvMenu);

        viewPager =findViewById(R.id.viewpage);
        rcvCategory = (RecyclerView) findViewById(R.id.rcvcategory);
        rcvProduct = (RecyclerView) findViewById(R.id.rcvproduct);
        imvHome = (ImageView) findViewById(R.id.imgHome);
        imvAccount = (ImageView) findViewById(R.id.imgAccount);
        imvLogout = (ImageView) findViewById(R.id.imgLogout);
        tvLogout = (TextView) findViewById(R.id.tvLogout);

        linearHome = (LinearLayout) findViewById(R.id.home);
        linearAccount = (LinearLayout) findViewById(R.id.account);
        linearCart = (LinearLayout) findViewById(R.id.cart);
        linearSupport = (LinearLayout) findViewById(R.id.support);
        linearLogout = (LinearLayout) findViewById(R.id.logout);
        linearLogin = findViewById(R.id.login);
        svProduct = (SearchView) findViewById(R.id.svproduct);
    }

//    private void ActionToolBar(){
//        toolbar.setNavigationIcon(R.drawable.sidebar);
//        toolbar.setPadding(16, 0, 0, 0);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });
//    }

//    private void MapListViewMenu(){
//        arrayList = new ArrayList<>();
//        arrayList.add(new ItemMenuModel("Java",R.drawable.sample_product));
//        arrayList.add(new ItemMenuModel("Java",R.drawable.sample_product));
//
//        adapter = new MenuCategoryAdapter(MainActivity.this, R.layout.item_menu, arrayList);
//        listView.setAdapter(adapter);
//    }

    private void SliderImage(){
        imagesList = getListImage();
        ImagesViewPageAdapter adapter = new ImagesViewPageAdapter(imagesList);
        viewPager.setAdapter(adapter);
        handler.postDelayed(runnable,3000);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private List<ImageModel> getListImage()
    {
        List<ImageModel>list = new ArrayList<>();
        list.add(new ImageModel(R.drawable.slider2));
        list.add(new ImageModel(R.drawable.slider1));
        list.add(new ImageModel(R.drawable.slider3));
        list.add(new ImageModel(R.drawable.slider4));
        list.add(new ImageModel(R.drawable.slider5));
        list.add(new ImageModel(R.drawable.slider6));

        return list;
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
    @Override
    protected  void onResume()
    {
        super.onResume();
        handler.postDelayed(runnable,3000);
    }


    private void searchProduct() {
        svProduct.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("keyword", s);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }


    private void getAllCategory() {
        ApiService apiService = ApiClient.getApiService();
        apiService.getAllCategory()
                .enqueue(new Callback<List<CategoryModel>>() {
                    @Override
                    public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                        categories = response.body();
                        categoryAdapter = new CategoryAdapter(getApplicationContext(), categories);
                        rcvCategory.setHasFixedSize(true);
                        rcvCategory.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        rcvCategory.setAdapter(categoryAdapter);
                        categoryAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                        Log.d("mainactivity", "call get all category fail");
                    }
                });
    }

    private void getLastProduct() {
        ApiService apiService = ApiClient.getApiService();
        apiService.lastProduct(0)
                .enqueue(new Callback<PageModel<ProductModel>>() {
                    @Override
                    public void onResponse(Call<PageModel<ProductModel>> call, Response<PageModel<ProductModel>> response) {
                        PageModel<ProductModel> pageProduct = response.body();
                        if(pageProduct != null) {
                            products = pageProduct.getContent();
                            page = pageProduct.getIndex();
                            total = pageProduct.getTotal();
                            productAdapter = new ProductAdapter(getApplicationContext(), products);
                            rcvProduct.setHasFixedSize(true);
                            rcvProduct.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
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
        apiService.lastProduct(page)
                .enqueue(new Callback<PageModel<ProductModel>>() {
                    @Override
                    public void onResponse(Call<PageModel<ProductModel>> call, Response<PageModel<ProductModel>> response) {
                        PageModel<ProductModel> pageProduct = response.body();
                        assert pageProduct != null;
                        products.addAll(pageProduct.getContent());
                        productAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<PageModel<ProductModel>> call, Throwable t) {

                    }
                });
    }

}