package com.kashta.kala.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.kashta.kala.database.entities.PortfolioItem;

import java.util.List;

@Dao
public interface PortfolioDao {

    @Insert
    void insert(PortfolioItem item);

    @Delete
    void delete(PortfolioItem item);

    @Query("SELECT * FROM portfolio ORDER BY id DESC")
    LiveData<List<PortfolioItem>> getAllItems();
}
