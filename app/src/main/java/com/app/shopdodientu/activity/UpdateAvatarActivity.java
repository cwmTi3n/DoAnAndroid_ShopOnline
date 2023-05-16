package com.app.shopdodientu.activity;

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
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.seller.RegisterSellerActivity;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.ShopModel;
import com.app.shopdodientu.model.UserModel;
import com.app.shopdodientu.util.Constant;
import com.app.shopdodientu.util.LoadingDialog;
import com.app.shopdodientu.util.UIHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAvatarActivity extends AppCompatActivity {
    private Uri mUri;
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
                            imageViewUpload.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
    );
    private TextView tvBack;
    private ImageView imageViewUpload;
    private Button btnUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_update_avatar);

        MapItemView();
        imageViewUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPermission();
            }
        });
        uploadAvatar();

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void uploadAvatar() {
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUri == null) {
                    Toast.makeText(getApplicationContext(), "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    LoadingDialog loadingDialog = new LoadingDialog(UpdateAvatarActivity.this);
                    loadingDialog.show();
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
                    apiService.updateAvatar(imageFile)
                            .enqueue(new Callback<UserModel>() {
                                @Override
                                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                                    UserModel userModel = response.body();
                                    if(userModel != null) {
                                        Constant.userLogin.setAvatar(userModel.getAvatar());
                                        Intent resultIntent = new Intent();
                                        setResult(Activity.RESULT_OK, resultIntent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                                    }
                                    loadingDialog.dismiss();

                                }

                                @Override
                                public void onFailure(Call<UserModel> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                                    loadingDialog.dismiss();
                                }
                            });
                }
            }
        });
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

    private void MapItemView(){
        tvBack = findViewById(R.id.tvBack);
        imageViewUpload = findViewById(R.id.imageViewUpload);
        btnUpload = findViewById(R.id.btnUpload);
    }
}