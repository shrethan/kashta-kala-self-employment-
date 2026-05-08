package com.kashta.kala.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "price_quotes")
public class PriceQuote {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "customer_name")
    public String customerName;

    @ColumnInfo(name = "design_name")
    public String designName;

    @ColumnInfo(name = "wood_type")
    public String woodType;

    @ColumnInfo(name = "length_ft")
    public double lengthFt;

    @ColumnInfo(name = "width_ft")
    public double widthFt;

    @ColumnInfo(name = "height_ft")
    public double heightFt;

    @ColumnInfo(name = "square_feet")
    public double squareFeet;

    @ColumnInfo(name = "wood_cost")
    public double woodCost;

    @ColumnInfo(name = "labor_cost")
    public double laborCost;

    @ColumnInfo(name = "total_cost")
    public double totalCost;

    @ColumnInfo(name = "notes")
    public String notes;

    @ColumnInfo(name = "created_at")
    public String createdAt;

    public PriceQuote(String customerName, String designName, String woodType,
                      double lengthFt, double widthFt, double heightFt,
                      double squareFeet, double woodCost, double laborCost,
                      double totalCost, String notes, String createdAt) {
        this.customerName = customerName;
        this.designName = designName;
        this.woodType = woodType;
        this.lengthFt = lengthFt;
        this.widthFt = widthFt;
        this.heightFt = heightFt;
        this.squareFeet = squareFeet;
        this.woodCost = woodCost;
        this.laborCost = laborCost;
        this.totalCost = totalCost;
        this.notes = notes;
        this.createdAt = createdAt;
    }
}
