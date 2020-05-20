package com.example.player.data.db;

import android.app.Application;
import androidx.room.Room;
import androidx.room.RoomDatabase;


public abstract class Database extends RoomDatabase {

    private static final String DATABASE_NAME = "Movie_db";

    public abstract MovieDao getMovieDao();

    private static Database instance;

    public static Database getInstance(Application application) {
        if (instance == null)
            instance = Room.databaseBuilder(application, Database.class, DATABASE_NAME).build();
        return instance;
    }
}
