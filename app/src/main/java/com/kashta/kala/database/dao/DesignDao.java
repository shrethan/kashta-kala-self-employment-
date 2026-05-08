package com.kashta.kala.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kashta.kala.database.entities.Design;

import java.util.List;

@Dao
public interface DesignDao {

    @Insert
    void insert(Design design);

    @Update
    void update(Design design);

    @Delete
    void delete(Design design);

    @Query("SELECT * FROM designs ORDER BY id ASC")
    LiveData<List<Design>> getAllDesigns();

    @Query("SELECT * FROM designs WHERE category = :category ORDER BY id ASC")
    LiveData<List<Design>> getDesignsByCategory(String category);

    @Query("SELECT * FROM designs WHERE is_favorited = 1 ORDER BY id ASC")
    LiveData<List<Design>> getFavoriteDesigns();

    @Query("UPDATE designs SET is_favorited = :isFav WHERE id = :id")
    void setFavorite(int id, boolean isFav);

    @Query("SELECT COUNT(*) FROM designs")
    int getTotalCount();

    @Query("SELECT COUNT(*) FROM designs WHERE is_favorited = 1")
    int getFavoriteCount();
}
