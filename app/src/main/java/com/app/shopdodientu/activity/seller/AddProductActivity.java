package com.app.shopdodientu.activity.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shopdodientu.R;

import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {
    private TextView tvBack;
    private ImageView imgProduct;
    private EditText edtName, edtDescription, edtPrice, edtStock;

    private Spinner snCateName;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        MapViewItem();
        AddItemToSpinnerCate();
        SpinnerCateClicked();

    }

    private void MapViewItem(){
        tvBack = (TextView) findViewById(R.id.tvBack);
        snCateName = findViewById(R.id.snCateName);
        imgProduct = (ImageView) findViewById(R.id.imgProduct);
        edtName = (EditText) findViewById(R.id.edtName);
        edtDescription = (EditText) findViewById(R.id.edtDescription);
        edtPrice = (EditText) findViewById(R.id.edtPrice);
        edtStock = (EditText) findViewById(R.id.edtStock);

    }
    private void AddItemToSpinnerCate() {
        String[] spinnerItems = {"Select an item", "Item 1", "Item 2", "Item 3", "Item 1", "Item 2", "Item 3", "Item 1", "Item 2", "Item 3", "Item 1", "Item 2", "Item 3", "Item 1", "Item 2", "Item 3", "Item 1", "Item 2", "Item 3"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snCateName.setAdapter(adapter);
    }

    private void SpinnerCateClicked(){
        snCateName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();
                // Do something with the selected option
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do something when nothing is selected
            }
        });
    }
}