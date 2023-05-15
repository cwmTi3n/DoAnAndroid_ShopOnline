package com.app.shopdodientu.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tinh")
public class TinhEntity implements Serializable {
    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "tinh_id")
    private String id;

    @ColumnInfo(name = "tinh_name")
    private String name;

    public TinhEntity() {
    }

    public TinhEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
