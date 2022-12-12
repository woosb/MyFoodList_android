package com.example.myfoodlist.room;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import org.jetbrains.annotations.NotNull;

@Database(entities = {MainData.class}, version = 2, exportSchema = false)
public abstract class RoomDb extends RoomDatabase {

    private static RoomDb database;
    private static String DATABASE_NAME = "database";

    public synchronized static RoomDb getInstance(Context context){
        if(database == null){
            database = Room.databaseBuilder(context.getApplicationContext(), RoomDb.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
        }
        return database;
    }

    public abstract MainDao mainDao();

    @NonNull
    @NotNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @NotNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
