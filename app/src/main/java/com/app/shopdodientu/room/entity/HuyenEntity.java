package com.app.shopdodientu.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "huyen", foreignKeys = @ForeignKey(entity = TinhEntity.class, parentColumns = "tinh_id", childColumns = "tinh_id", onDelete = ForeignKey.CASCADE))
public class HuyenEntity {
    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "huyen_id")
    private String id;
    @ColumnInfo(name = "huyen_name")
    private String name;
    @ColumnInfo(name = "tinh_id")
    private String tinhId;

    public HuyenEntity() {
    }

    public HuyenEntity(String id, String name, String tinhId) {
        this.id = id;
        this.name = name;
        this.tinhId = tinhId;
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

    public String getTinhId() {
        return tinhId;
    }

    public void setTinhId(String tinhId) {
        this.tinhId = tinhId;
    }
}
