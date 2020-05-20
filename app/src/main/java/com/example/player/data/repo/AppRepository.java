package com.example.player.data.repo;

import com.example.player.model.Movie;

import java.util.List;

public interface AppRepository {
    void addMovie(Movie movie);

    Movie getMovie(int id);

    List<Movie> getAllMovies();

    void removeMovie(int id);

    void clearAll();
}
