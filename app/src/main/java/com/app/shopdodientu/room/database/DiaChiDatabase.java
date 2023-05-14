package com.app.shopdodientu.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.app.shopdodientu.room.dao.HuyenDao;
import com.app.shopdodientu.room.dao.TinhDao;
import com.app.shopdodientu.room.dao.XaDao;
import com.app.shopdodientu.room.entity.HuyenEntity;
import com.app.shopdodientu.room.entity.TinhEntity;
import com.app.shopdodientu.room.entity.XaEntity;

@Database(entities = {TinhEntity.class, HuyenEntity.class, XaEntity.class}, version = 1)
public abstract class DiaChiDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "diachi.db";
    private static DiaChiDatabase instance;
    public static synchronized DiaChiDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DiaChiDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract XaDao xaDao();
    public abstract HuyenDao huyenDao();
    public abstract TinhDao tinhDao();
}
