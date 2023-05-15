package com.app.shopdodientu.activity.AddressTabLayout;

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
import com.app.shopdodientu.room.dao.XaDao;
import com.app.shopdodientu.room.database.DiaChiDatabase;
import com.app.shopdodientu.room.entity.XaEntity;

import java.util.List;
import java.util.Map;

public class WardFragment extends Fragment {

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
            Log.d("xa", String.valueOf(xaEntities.size()));
            xaAdapter = new XaAdapter(getContext(),xaEntities);
            rcvWard.setHasFixedSize(true);
            rcvWard.setLayoutManager(new GridLayoutManager(getContext(), 1));
            rcvWard.setAdapter(xaAdapter);
            xaAdapter.notifyDataSetChanged();
        }
    }
}
