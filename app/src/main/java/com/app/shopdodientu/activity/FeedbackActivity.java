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
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shopdodientu.R;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.FeedbackModel;
import com.app.shopdodientu.model.ProductModel;
import com.app.shopdodientu.util.Constant;
import com.app.shopdodientu.util.LoadingDialog;
import com.app.shopdodientu.util.UIHelper;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

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

public class FeedbackActivity extends AppCompatActivity {
    private RatingBar ratingBar;
    private TextView tvLevelRating, tvNameAccountFeedback, tvBack, tvSend, tvProductName;
    private Switch switchName;
    private ImageView imgProduct, imgFeedback1;
    private EditText edtFeedback;

    private Uri mUri;
    private int product_id;

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
                    Log.e("TAG", "onActivityResult");
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
                            imgFeedback1.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
    );

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_feedback);
        product_id = getIntent().getIntExtra("productId", 0);
        MapItemView();
        RatingBarChange();
        SwitchChange();
        TextViewBackClicked();
        renderView();
    }

    private void renderView() {
        String productname = getIntent().getStringExtra("productname");
        String image = getIntent().getStringExtra("image");

        Glide.with(FeedbackActivity.this)
                .load(image)
                .into(imgProduct);
        tvProductName.setText(productname);
        imgFeedback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPermission();
            }
        });
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog loadingDialog = new LoadingDialog(FeedbackActivity.this);
                loadingDialog.show();
                String contentString = String.valueOf(edtFeedback.getText());
                String starString = String.valueOf((int)ratingBar.getRating());
                String productIdString = String.valueOf(product_id);
                RequestBody content = RequestBody.create(MediaType.parse("multipart/form-data"), contentString);
                RequestBody star = RequestBody.create(MediaType.parse("multipart/form-data"), starString);
                RequestBody productId = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(product_id));

                MultipartBody.Part imageFile = null;
                if(mUri != null) {
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
                }
                ApiService apiService = ApiClient.getApiService();
                apiService.createFeedback(content, star, productId, imageFile)
                        .enqueue(new Callback<FeedbackModel>() {
                            @Override
                            public void onResponse(Call<FeedbackModel> call, Response<FeedbackModel> response) {
                                loadingDialog.dismiss();
                                if(response.body() != null) {
                                    Toast.makeText(getApplicationContext(), "Feedback của bạn đã được lưu", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<FeedbackModel> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                                loadingDialog.dismiss();
                            }
                        });
            }
        });
    }

    private void MapItemView(){
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        tvLevelRating = (TextView) findViewById(R.id.tvLevel);
        tvNameAccountFeedback = (TextView) findViewById(R.id.tvSetName);
        switchName = (Switch) findViewById(R.id.switchName);
        tvBack = findViewById(R.id.tvBack);
        tvSend = findViewById(R.id.tvSend);
        tvProductName = findViewById(R.id.tvProductName);
        imgProduct = findViewById(R.id.imgProduct);
        imgFeedback1 = findViewById(R.id.imgFeedback1);
//        imgFeedback2 = findViewById(R.id.imgFeedback2);
        edtFeedback = findViewById(R.id.edtFeedback);
    }

    private void RatingBarChange(){
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rating = ratingBar.getRating();

                if(rating <= 1 && rating >0){
                    tvLevelRating.setText("Bad");
                }
                else if(rating > 1 && rating <= 2){
                    tvLevelRating.setText("Unsatisfied");
                }
                else if(rating > 2 && rating <= 3){
                    tvLevelRating.setText("Normal");
                }
                else if(rating > 3 && rating <= 4){
                    tvLevelRating.setText("Satisfied");
                }
                else {
                    tvLevelRating.setText("Wonderful");
                }
            }
        });
    }

    private void SwitchChange(){
        switchName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    tvNameAccountFeedback.setText("Account name will show up as s***");
                }
                else {
                    tvNameAccountFeedback.setText("Account name will show up as NAME");
                }
            }
        });
    }

    private void TextViewBackClicked(){
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}