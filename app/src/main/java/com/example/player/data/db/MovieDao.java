package com.example.player.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.player.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMovie(Movie movie);

    @Query("SELECT * FROM MOVIE WHERE _id = :id")
    Movie getMovie(int id);

    @Query("SELECT * FROM MOVIE")
    List<Movie> getAllMovies();

    @Query("DELETE FROM MOVIE WHERE _id = :id")
    void removeMovie(int id);

    @Query("DELETE FROM MOVIE")
    void clearAll();
}
