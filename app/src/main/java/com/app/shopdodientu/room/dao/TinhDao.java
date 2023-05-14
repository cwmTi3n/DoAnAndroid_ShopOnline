package com.app.shopdodientu.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.shopdodientu.room.entity.TinhEntity;

import java.util.List;


@Dao
public interface TinhDao {
    @Query("SELECT * FROM tinh")
    List<TinhEntity> getAll();

    @Insert
    void insertAll(TinhEntity...tinhEntities);
    @Update
    void update(TinhEntity... tinhEntities);

    @Delete
    void delete(TinhEntity tinhEntity);
}
