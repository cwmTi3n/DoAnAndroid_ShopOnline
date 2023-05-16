package com.app.shopdodientu.activity.seller;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.AddressTabLayout.LocationActivity;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.ShopModel;
import com.app.shopdodientu.model.UserModel;
import com.app.shopdodientu.util.Constant;
import com.app.shopdodientu.util.LoadingDialog;
import com.app.shopdodientu.util.UIHelper;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterSellerActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> addressSelectionLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        userAddress = data.getStringExtra("selected_address");
                        // Hiển thị địa chỉ đã chọn trên giao diện thanh toán
                        displaySelectedAddress();
                    }
                }
            }
    );

    private void CheckPermission() {
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M) {
            openGallery();
            return;
        }
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        }
        else {
            requestPermissions(Constant.permissions(), Constant.MY_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == Constant.MY_REQUEST_CODE) {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        }
    }

    private void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select"));
    }

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        //request code
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imgBanner.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
    );

    private void displaySelectedAddress() {
        editAddress.setText(userAddress);
    }

    private Uri mUri;

    private String userAddress;
    private ImageView imgBanner, imgAvatar;
    private TextView tvUploadBanner, tvUploadAvatar;

    private EditText edtShopName, editAddress, editEmail, edtPhone;
    private Button btnSave, btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_register_seller);

        MapItemView();
        TextViewAddressClicked();
        Back();
        gotoMainSeller();
        renderView();
        uploadBanner();
    }

    private void MapItemView(){
        imgBanner = findViewById(R.id.imgBanner);
        imgAvatar = findViewById(R.id.imgAvatar);
        tvUploadBanner = findViewById(R.id.tvUploadBanner);
        tvUploadAvatar = findViewById(R.id.tvUploadAvatar);
        edtShopName = findViewById(R.id.edtShopName);
        editEmail = findViewById(R.id.editEmail);
        edtPhone = findViewById(R.id.edtPhone);
        editAddress = findViewById(R.id.editAddress);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);
    }

    private void uploadBanner() {
        tvUploadBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPermission();
            }
        });
    }

    private void renderView() {
        UserModel userModel = Constant.userLogin;
        Glide.with(getApplicationContext())
                .load(userModel.getAvatar())
                .into(imgAvatar);
        edtShopName.setText(userModel.getUsername());
        editEmail.setText(userModel.getEmail());
        edtPhone.setText(userModel.getPhone());
        SharedPreferences sharedPreferences = getSharedPreferences("address", MODE_PRIVATE);
        userAddress = sharedPreferences.getString("user_address", null);
        if(userAddress != null) {
            editAddress.setText(userAddress);
        }
    }

    private void TextViewAddressClicked() {
        editAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterSellerActivity.this, LocationActivity.class);
                addressSelectionLauncher.launch(intent);
            }
        });
    }

    private void Back(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void gotoMainSeller(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Constant.userLogin.getRole().equals("USER")) {
                    registerSeller();
                }
                else {
                    setUpShop();
                }
            }
        });
    }

    private void setUpShop() {
        if(userAddress == null || mUri == null) {
            Toast.makeText(getApplicationContext(), "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else {
            LoadingDialog loadingDialog = new LoadingDialog(RegisterSellerActivity.this);
            loadingDialog.show();
            RequestBody address = RequestBody.create(MediaType.parse("multipart/form-data"), userAddress);
            MultipartBody.Part imageFile = null;
            InputStream inputStream = null;
            try {
                ContentResolver contentResolver = getContentResolver();
                inputStream = contentResolver.openInputStream(mUri);
                File file = createFileFromInputStream(inputStream);
                // Tạo RequestBody từ File
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                imageFile = MultipartBody.Part.createFormData("imageFile", file.getName(), requestFile);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            ApiService apiService = ApiClient.getApiService();
            apiService.updateBanner(address, imageFile)
                    .enqueue(new Callback<ShopModel>() {
                        @Override
                        public void onResponse(Call<ShopModel> call, Response<ShopModel> response) {
                            loadingDialog.dismiss();
                            ShopModel shopModel = response.body();
                            if(shopModel != null) {
                                Constant.userLogin.setBannerShop(shopModel.getBannerShop());
                                Toast.makeText(getApplicationContext(), "Ảnh shop đã được cập nhật", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ShopModel> call, Throwable t) {
                            loadingDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void registerSeller() {
        if(userAddress == null || mUri == null) {
            Toast.makeText(getApplicationContext(), "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else {
            LoadingDialog loadingDialog = new LoadingDialog(RegisterSellerActivity.this);
            loadingDialog.show();
            RequestBody address = RequestBody.create(MediaType.parse("multipart/form-data"), userAddress);
            MultipartBody.Part imageFile = null;
            InputStream inputStream = null;
            try {
                ContentResolver contentResolver = getContentResolver();
                inputStream = contentResolver.openInputStream(mUri);
                File file = createFileFromInputStream(inputStream);
                // Tạo RequestBody từ File
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                imageFile = MultipartBody.Part.createFormData("imageFile", file.getName(), requestFile);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            ApiService apiService = ApiClient.getApiService();
            apiService.registerSeller(address, imageFile)
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            loadingDialog.dismiss();
                            Constant.userLogin.setRole("SELLER");
                            Intent intent = new Intent(RegisterSellerActivity.this, MainSellerActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            loadingDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }

    private File createFileFromInputStream(InputStream inputStream) throws IOException {
        File file = new File(getCacheDir(), "temp_file");
        OutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.close();
        return file;
    }



}