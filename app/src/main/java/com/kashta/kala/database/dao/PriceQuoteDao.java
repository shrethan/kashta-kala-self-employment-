package com.kashta.kala.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.kashta.kala.database.entities.PriceQuote;

import java.util.List;

@Dao
public interface PriceQuoteDao {

    @Insert
    void insert(PriceQuote quote);

    @Delete
    void delete(PriceQuote quote);

    @Query("SELECT * FROM price_quotes ORDER BY id DESC")
    LiveData<List<PriceQuote>> getAllQuotes();

    @Query("SELECT COUNT(*) FROM price_quotes")
    int getTotalCount();

    @Query("SELECT SUM(total_cost) FROM price_quotes")
    double getTotalRevenue();
}
