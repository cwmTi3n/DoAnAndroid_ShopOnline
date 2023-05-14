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
import com.app.shopdodientu.adapter.HuyenAdapter;
import com.app.shopdodientu.databinding.FragmentDistrictBinding;
import com.app.shopdodientu.databinding.FragmentProvinceBinding;
import com.app.shopdodientu.room.dao.HuyenDao;
import com.app.shopdodientu.room.database.DiaChiDatabase;
import com.app.shopdodientu.room.entity.HuyenEntity;

import java.util.List;

public class DistrictFragment extends Fragment implements HuyenAdapter.OnHuyenClickListener{
    private RecyclerView rcvHuyen;
    private TextView tvNameDistrict;
    private HuyenAdapter huyenAdapter;
    private List<HuyenEntity> huyenEntities;
    private ViewPager2 viewPager;
    private static String huyenId;
    static String getHuyenId() {
        String tmp = huyenId;
        huyenId = null;
        return tmp;
    }

    FragmentDistrictBinding binding;
    public DistrictFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = FragmentDistrictBinding.inflate(inflater, container, false);
        MapItemView();
        viewPager = getActivity().findViewById(R.id.viewPager2);
        //vi tri load du lieu: recyclerview
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadHuyenByTinh();
    }

    private void MapItemView(){
        rcvHuyen = binding.getRoot().findViewById(R.id.rcvDistrict);
        tvNameDistrict = binding.getRoot().findViewById(R.id.name);
    }

    private void loadHuyenByTinh() {
        HuyenDao huyenDao = DiaChiDatabase.getInstance(getContext()).huyenDao();
        String tinhId = ProvinceFragment.getTinhId();
        if(tinhId != null) {
            huyenEntities = huyenDao.getHuyenByTinhId(tinhId);
            huyenAdapter = new HuyenAdapter(getContext(), huyenEntities);
            rcvHuyen.setHasFixedSize(true);
            rcvHuyen.setLayoutManager(new GridLayoutManager(getContext(), 1));
            rcvHuyen.setAdapter(huyenAdapter);
            huyenAdapter.setOnHuyenClickListener(this);
            huyenAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onHuyenClick(String Id) {
        huyenId = Id;
        viewPager.setCurrentItem(2);
    }
}
