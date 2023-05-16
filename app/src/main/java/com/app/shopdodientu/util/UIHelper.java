package com.app.shopdodientu.util;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.CartActivity;
import com.app.shopdodientu.activity.CheckOutActivity;
import com.app.shopdodientu.activity.ShopTabLayout.HomeShopActivity;
import com.app.shopdodientu.activity.LoginActivity;
import com.app.shopdodientu.activity.MainActivity;
import com.app.shopdodientu.activity.MyAccountActivity;
import com.app.shopdodientu.activity.ProfileActivity;
import com.app.shopdodientu.activity.SearchActivity;
import com.app.shopdodientu.activity.SupportActivity;
import com.app.shopdodientu.activity.seller.AddProductActivity;
import com.app.shopdodientu.activity.seller.MainSellerActivity;
import com.app.shopdodientu.activity.seller.RegisterSellerActivity;
import com.app.shopdodientu.activity.seller.WelcomeSellerActivity;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UIHelper {
    public static void fullscreen(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void gotoAccount(LinearLayout linear, Context context, ActivityResultLauncher<Intent> activityResultLauncher) {
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(Constant.userLogin == null) {
                    intent = new Intent(context, LoginActivity.class);
                    activityResultLauncher.launch(intent);
                }
                else {
                    intent = new Intent(context, MyAccountActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    public static void gotoHome(LinearLayout linear, Context context) {
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    public static void gotoCart(LinearLayout linear, Context context, ActivityResultLauncher<Intent> activityResultLauncher) {
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(Constant.userLogin == null) {
                    intent = new Intent(context, LoginActivity.class);
                    activityResultLauncher.launch(intent);
                }
                else {
                    intent = new Intent(context, CartActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    public static void gotoSupport(ImageView imv, Context context) {
        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, SupportActivity.class);
                context.startActivity(intent);
            }
        });
    }

    public static void gotoCheckOUt(Button btn, Context context) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, CheckOutActivity.class);
                context.startActivity(intent);
            }
        });
    }


    public static void logout(LinearLayout linear, Context context) {
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiService apiService = ApiClient.getApiService();
                apiService.logout()
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                ApiClient.restApiService();
                            }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                ApiClient.restApiService();
                            }
                        });
                SharedPreferences sharedPreferences = context.getSharedPreferences("dataLogin", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("username");
                editor.remove("password");
                editor.commit();
                Constant.userLogin = null;
                Intent intent;
                intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }





    public static void gotoHomeShop(TextView tv, Context context, int sellerId) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, HomeShopActivity.class);
                intent.putExtra("sellerId", sellerId);
                context.startActivity(intent);
            }
        });
    }

    public static void CheckEmail(EditText edtEmail, Context context){
        String email = edtEmail.getText().toString().trim();
        if (email.isEmpty() || !email.contains("@") || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(context, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public static void CheckInputNotEmpty(EditText edt, Context context){
        String input = edt.getText().toString().trim();
        if (input.isEmpty() ) {
            Toast.makeText(context, "Dữ liệu không được trống", Toast.LENGTH_SHORT).show();
            return;
        }
    }


}
