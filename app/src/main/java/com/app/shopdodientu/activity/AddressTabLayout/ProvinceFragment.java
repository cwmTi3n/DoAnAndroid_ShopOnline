package com.app.shopdodientu.activity.AddressTabLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.app.shopdodientu.R;
import com.app.shopdodientu.adapter.TinhAdapter;
import com.app.shopdodientu.databinding.FragmentProvinceBinding;
import com.app.shopdodientu.databinding.FragmentWardBinding;
import com.app.shopdodientu.room.dao.TinhDao;
import com.app.shopdodientu.room.database.DiaChiDatabase;
import com.app.shopdodientu.room.entity.TinhEntity;

import java.util.List;

public class ProvinceFragment extends Fragment implements TinhAdapter.OnTinhClickListener{

    private RecyclerView rcvTinh;
    private ViewPager2 viewPager;

    FragmentProvinceBinding binding;
    private TinhAdapter tinhAdapter;
    private List<TinhEntity> tinhEntities;
    private static String tinhId;
    static String getTinhId() {
        String tmp = tinhId;
        tinhId = null;
        return tmp;
    }
    public ProvinceFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = FragmentProvinceBinding.inflate(inflater, container, false);
        viewPager = getActivity().findViewById(R.id.viewPager2);
        MapItemView();

        //vi tri load du lieu: recyclerview
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadTinh();
    }

    private void MapItemView(){
        rcvTinh = binding.getRoot().findViewById(R.id.rcvProvince);
    }

    private void loadTinh() {
        TinhDao tinhDao = DiaChiDatabase.getInstance(getContext()).tinhDao();
        tinhEntities = tinhDao.getAll();
        tinhAdapter = new TinhAdapter(getContext(), tinhEntities);
        rcvTinh.setHasFixedSize(true);
        rcvTinh.setLayoutManager(new GridLayoutManager(getContext(), 1));
        rcvTinh.setAdapter(tinhAdapter);
        tinhAdapter.setOnTinhClickListener(this);
        tinhAdapter.notifyDataSetChanged();
    }

    @Override
    public void onTinhClick(String Id) {
        tinhId = Id;
        viewPager.setCurrentItem(1);
    }
}
