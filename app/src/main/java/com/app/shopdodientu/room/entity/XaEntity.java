package com.app.shopdodientu.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "xa", foreignKeys = @ForeignKey(entity = HuyenEntity.class, parentColumns = "huyen_id", childColumns = "huyen_id", onDelete = ForeignKey.CASCADE))
public class XaEntity {
    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "xa_id")
    private String id;
    @ColumnInfo(name = "xa_name")

    private String name;
    @ColumnInfo(name = "huyen_id")

    private String huyenId;

    public XaEntity() {
    }

    public XaEntity(String id, String name, String huyenId) {
        this.id = id;
        this.name = name;
        this.huyenId = huyenId;
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

    public String getHuyenId() {
        return huyenId;
    }

    public void setHuyenId(String huyenId) {
        this.huyenId = huyenId;
    }
}
