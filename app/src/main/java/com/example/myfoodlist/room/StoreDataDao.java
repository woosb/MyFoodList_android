package com.example.myfoodlist.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.time.LocalDate;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface StoreDataDao {

    @Insert(onConflict = REPLACE)
    void insert(StoreData storeData);

    @Delete
    void delete(StoreData storeData);

    @Delete
    void reset(List<StoreData> storeDates);

    @Query("UPDATE store set score = :sScore, modYmd = :sModYmd where ID = :sId")
    void updateScore(double sScore, LocalDate sModYmd, Long sId);

    @Query("SELECT * FROM store WHERE score > :sScore")
    List<StoreData> getStoreDataWithScore(double sScore);

    @Query("SELECT * fROM store order by name")
    List<StoreData> getAll();

}

