package com.app.shopdodientu.activity.AddressTabLayout;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.adapter.XaAdapter;
import com.app.shopdodientu.databinding.FragmentDistrictBinding;
import com.app.shopdodientu.databinding.FragmentWaittingorderBinding;
import com.app.shopdodientu.databinding.FragmentWardBinding;
import com.app.shopdodientu.room.dao.HuyenDao;
import com.app.shopdodientu.room.dao.TinhDao;
import com.app.shopdodientu.room.dao.XaDao;
import com.app.shopdodientu.room.database.DiaChiDatabase;
import com.app.shopdodientu.room.entity.HuyenEntity;
import com.app.shopdodientu.room.entity.XaEntity;

import java.util.List;
import java.util.Map;

public class WardFragment extends Fragment implements XaAdapter.OnXaClickListener{

    private RecyclerView rcvWard;
    private XaAdapter xaAdapter;
    private List<XaEntity> xaEntities;
    private TextView tvNameWard;
    FragmentWardBinding binding;
    public WardFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = FragmentWardBinding.inflate(inflater, container, false);
        MapItemView();
        //vi tri load du lieu: recyclerview
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadXaByHuyen();
    }

    private void MapItemView(){
        rcvWard = binding.getRoot().findViewById(R.id.rcvWard);
        tvNameWard = binding.getRoot().findViewById(R.id.name);
    }

    private void loadXaByHuyen() {
        XaDao xaDao = DiaChiDatabase.getInstance(getContext()).xaDao();
        String huyenId = DistrictFragment.getHuyenId();
        if(huyenId != null) {
            xaEntities = xaDao.getXaByHuyenId(huyenId);
            xaAdapter = new XaAdapter(getContext(),xaEntities);
            rcvWard.setHasFixedSize(true);
            rcvWard.setLayoutManager(new GridLayoutManager(getContext(), 1));
            rcvWard.setAdapter(xaAdapter);
            xaAdapter.setOnXaClickListener(this);
            xaAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onXaClick(String Id, String name) {
        HuyenDao huyenDao = DiaChiDatabase.getInstance(getContext()).huyenDao();
        TinhDao tinhDao = DiaChiDatabase.getInstance(getContext()).tinhDao();
        HuyenEntity huyen = huyenDao.getHuyenById(DistrictFragment.getHuyenId());
        String tinh = tinhDao.getTinhById(ProvinceFragment.getTinhId());
        String diachi = name + ", " + huyen.getName() + ", " + tinh;
        Intent resultIntent = new Intent();
        resultIntent.putExtra("selected_address", diachi);
        getActivity().setResult(Activity.RESULT_OK, resultIntent);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("address", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_address", diachi);
        editor.apply();
        getActivity().finish();
    }
}
