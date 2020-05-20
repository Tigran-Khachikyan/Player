package com.example.player.data.repo;

import com.example.player.data.db.MovieDao;
import com.example.player.model.Movie;

import java.util.List;

public class Repository implements AppRepository {

    private MovieDao movieDao;

    public Repository(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public void addMovie(Movie movie) {
        movieDao.addMovie(movie);
    }

    @Override
    public Movie getMovie(int id) {
        return movieDao.getMovie(id);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieDao.getAllMovies();
    }

    @Override
    public void removeMovie(int id) {
        movieDao.removeMovie(id);
    }

    @Override
    public void clearAll() {
        movieDao.clearAll();
    }
}
