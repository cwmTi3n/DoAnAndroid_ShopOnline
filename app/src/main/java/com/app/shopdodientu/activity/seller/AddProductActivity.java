package com.app.shopdodientu.activity.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.shopdodientu.R;

import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {

    Spinner snCateName;
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
        snCateName = findViewById(R.id.spinnercateName);

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