package com.app.shopdodientu.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.shopdodientu.room.entity.XaEntity;

import java.util.List;

@Dao
public interface XaDao {
    @Query("SELECT * FROM xa")
    List<XaEntity> getAll();

    @Insert
    void insertAll(XaEntity...xaEntities);

    @Update
    void update(XaEntity...xaEntities);

    @Query("SELECT * FROM xa WHERE xa.huyen_id = :huyenId")
    List<XaEntity> getXaByHuyenId(String huyenId);

    @Query("select * from xa where xa_id = :xaId")
    XaEntity getXaById(String xaId);

}
