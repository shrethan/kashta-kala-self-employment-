package com.kashta.kala.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "portfolio")
public class PortfolioItem {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "image_uri")
    public String imageUri;

    @ColumnInfo(name = "category")
    public String category;

    @ColumnInfo(name = "created_at")
    public String createdAt;

    public PortfolioItem(String title, String description, String imageUri, String category, String createdAt) {
        this.title = title;
        this.description = description;
        this.imageUri = imageUri;
        this.category = category;
        this.createdAt = createdAt;
    }
}
