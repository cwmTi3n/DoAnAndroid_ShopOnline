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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.ProductDetailActivity;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.CategoryModel;
import com.app.shopdodientu.model.ProductModel;
import com.app.shopdodientu.util.Constant;
import com.app.shopdodientu.util.LoadingDialog;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProductActivity extends AppCompatActivity {
    private TextView tvBack;
    private ImageView imgProduct;
    private EditText edtname, edtprice, edtstock, edtdescription;
    private RadioButton radioButtonOn, radioButtonOff;
    private Button btnSave, btnDelete;
    private Spinner snCateName;
    ArrayAdapter<CategoryModel> adapter;
    private int categoryInt;
    private Uri mUri;
    private  ProductModel productModel;
    private RadioGroup radioGroup;

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
        setContentView(R.layout.activity_update_product);

        MapItemView();
        renderView();
        updateProduct();
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog dialog = new LoadingDialog(UpdateProductActivity.this);
                dialog.show();
                ApiService apiService = ApiClient.getApiService();
                apiService.deleteProduct(productModel.getId())
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Xóa không thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
            }
        });
        AddItemToSpinnerCate();
        SpinnerCateClicked();


    }

    private void MapItemView(){
        snCateName = findViewById(R.id.sncateName);
        tvBack = findViewById(R.id.tvBack);
        imgProduct = findViewById(R.id.imgProduct);
        edtname = findViewById(R.id.edtname);
        edtprice = findViewById(R.id.edtprice);
        edtstock = findViewById(R.id.edtstock);
        edtdescription = findViewById(R.id.edtdescription);
        radioButtonOn = findViewById(R.id.radioButtonOn);
        radioButtonOff = findViewById(R.id.radioButtonOff);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        radioGroup = findViewById(R.id.rdoStatus);
    }

    private void AddItemToSpinnerCate() {
        ApiService apiService = ApiClient.getApiService();
        apiService.getAllCategory()
                .enqueue(new Callback<List<CategoryModel>>() {
                    @Override
                    public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                        List<CategoryModel> categoryModels = response.body();
                        int i = 0;
                        for(CategoryModel c : categoryModels) {
                            if(c.getId() == productModel.getCategoryId()) {
                                break;
                            }
                            i++;
                        }
                        adapter = new ArrayAdapter<CategoryModel>(UpdateProductActivity.this, android.R.layout.simple_spinner_item, categoryModels);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        snCateName.setAdapter(adapter);
                        snCateName.setSelection(i);
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

    private void updateProduct() {
        imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPermission();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog loadingDialog = new LoadingDialog(UpdateProductActivity.this);
                loadingDialog.show();
                String nameString = edtname.getText().toString();
                String descriptionString = edtdescription.getText().toString();
                String priceString = edtprice.getText().toString();
                String stockString = edtstock.getText().toString();
                RequestBody id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(productModel.getId()));
                RequestBody userId = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(Constant.userLogin.getId()));
                RequestBody name = RequestBody.create(MediaType.parse("multipart/form-data"), nameString);
                RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
                RequestBody price = RequestBody.create(MediaType.parse("multipart/form-data"), priceString);
                RequestBody stock = RequestBody.create(MediaType.parse("multipart/form-data"), stockString);
                RequestBody categoryId = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(categoryInt));
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);
                String statusString = selectedRadioButton.getText().toString();
                RequestBody status = RequestBody.create(MediaType.parse("multipart/form-data"), statusString);
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
                apiService.updateProduct(id, userId, name, description, price, stock, imageFile, categoryId, status)
                        .enqueue(new Callback<ProductModel>() {
                            @Override
                            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                                loadingDialog.dismiss();
                                ProductModel productRp = response.body();
                                if(productRp != null) {
                                    Toast.makeText(getApplicationContext(), "Cập nhật sản phẩm thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
                                    intent.putExtra("product", productRp);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Cập nhật sản phẩm không thành công", Toast.LENGTH_SHORT).show();
                                }
                                finish();
                            }

                            @Override
                            public void onFailure(Call<ProductModel> call, Throwable t) {
                                loadingDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Cập nhật sản phẩm không thành công", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

    }
    private void renderView() {
        productModel = (ProductModel) getIntent().getSerializableExtra("product");
        if(productModel != null) {
            Glide.with(getApplicationContext())
                    .load(productModel.getImage())
                    .into(imgProduct);
            edtname.setText(productModel.getName());
            edtdescription.setText(productModel.getDescription());
            edtprice.setText(String.valueOf(productModel.getPrice()));
            edtstock.setText(String.valueOf(productModel.getStock()));
            if(productModel.getStatus() == 0) {
                radioButtonOff.setChecked(true);
            }
            else {
                radioButtonOn.setChecked(true);
            }
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