package com.example.myfoodlist.room;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import org.jetbrains.annotations.NotNull;

@Database(entities = {StoreData.class}, version = 3, exportSchema = false)
public abstract class StoreDb extends RoomDatabase {

    private static StoreDb database;
    private static String DATABASE_NAME = "database";

    public synchronized static StoreDb getInstance(Context context){
        if(database == null){
            database = Room.databaseBuilder(context.getApplicationContext(), StoreDb.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
        }
        return database;
    }

    public abstract StoreDataDao storeDataDao();

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
