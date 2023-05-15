package com.app.shopdodientu.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.shopdodientu.room.entity.HuyenEntity;
import com.app.shopdodientu.room.entity.TinhEntity;

import java.util.List;

@Dao
public interface HuyenDao {
    @Query("SELECT * FROM huyen")
    List<TinhEntity> getAll();

    @Insert
    void insertAll(HuyenEntity...huyenEntities);
    @Update
    void update(HuyenEntity... huyenEntities);

    @Delete
    void delete(HuyenEntity huyenEntity);

    @Query("SELECT * FROM huyen WHERE huyen.tinh_id = :tinhId")
    List<HuyenEntity> getHuyenByTinhId(String tinhId);
}
