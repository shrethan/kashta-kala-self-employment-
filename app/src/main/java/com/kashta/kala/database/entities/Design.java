package com.kashta.kala.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "designs")
public class Design {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "category")
    public String category;

    @ColumnInfo(name = "image_url")
    public String imageUrl;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "is_favorited")
    public boolean isFavorited;

    public Design(String name, String category, String imageUrl, String description) {
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.description = description;
        this.isFavorited = false;
    }
}
