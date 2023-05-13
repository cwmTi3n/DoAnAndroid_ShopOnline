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
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shopdodientu.R;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.CategoryModel;
import com.app.shopdodientu.model.ProductModel;
import com.app.shopdodientu.util.Constant;
import com.app.shopdodientu.util.LoadingDialog;
import com.app.shopdodientu.util.RealPathUtil;
import com.app.shopdodientu.util.UIHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductActivity extends AppCompatActivity {
    private ImageView imgProduct;
    private EditText edtName, edtDescription, edtPrice, edtStock;
    private Button btnAddproduct;
    private int categoryInt;

    private Spinner snCateName;
    ArrayAdapter<CategoryModel> adapter;
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
                            imgProduct.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_add_product);
        MapViewItem();
        addProduct();
        AddItemToSpinnerCate();
        SpinnerCateClicked();

    }

    private void addProduct() {
        imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPermission();
            }
        });
        btnAddproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUri != null) {
                    LoadingDialog loadingDialog = new LoadingDialog(AddProductActivity.this);
                    loadingDialog.show();
                    String nameString = edtName.getText().toString();
                    String descriptionString = edtDescription.getText().toString();
                    String priceString = edtPrice.getText().toString();
                    String stockString = edtStock.getText().toString();
                    RequestBody name = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(nameString));
                    RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(descriptionString));
                    RequestBody price = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(priceString));
                    RequestBody stock = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(stockString));
                    RequestBody categoryId = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(categoryInt));
                    InputStream inputStream = null;
                    try {
                        ContentResolver contentResolver = getContentResolver();
                        inputStream = contentResolver.openInputStream(mUri);
                        File file = createFileFromInputStream(inputStream);

                        // Tạo RequestBody từ File
                        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                        // Tạo MultipartBody.Part từ RequestBody
                        MultipartBody.Part imageFile = MultipartBody.Part.createFormData("imageFile", file.getName(), requestFile);
                        ApiService apiService = ApiClient.getApiService();
                        apiService.addProduct(name, description, price, stock, imageFile, categoryId)
                                .enqueue(new Callback<ProductModel>() {
                                    @Override
                                    public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                                        loadingDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(AddProductActivity.this, MainSellerActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(Call<ProductModel> call, Throwable t) {
                                        loadingDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Thêm sản không thành công", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        // Sử dụng imageFile trong yêu cầu gửi đi
                        // ...
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
                else {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập hình ảnh cho sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Phương thức để tạo File từ InputStream
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

    private void MapViewItem(){
        snCateName = findViewById(R.id.snCateName);
        imgProduct = (ImageView) findViewById(R.id.imgProduct);
        edtName = (EditText) findViewById(R.id.edtName);
        edtDescription = (EditText) findViewById(R.id.edtDescription);
        edtPrice = (EditText) findViewById(R.id.edtPrice);
        edtStock = (EditText) findViewById(R.id.edtStock);
        btnAddproduct = (Button) findViewById(R.id.btnadd);
    }
    private void AddItemToSpinnerCate() {
        ApiService apiService = ApiClient.getApiService();
        apiService.getAllCategory()
                .enqueue(new Callback<List<CategoryModel>>() {
                    @Override
                    public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                        List<CategoryModel> categoryModels = response.body();
                        adapter = new ArrayAdapter<CategoryModel>(AddProductActivity.this, android.R.layout.simple_spinner_item, categoryModels);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        snCateName.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                    }
                });

    }

    private void SpinnerCateClicked(){
        snCateName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CategoryModel selectedOption = (CategoryModel) parent.getItemAtPosition(position);
                categoryInt = selectedOption.getId();
                // Do something with the selected option
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do something when nothing is selected
            }
        });
    }
}