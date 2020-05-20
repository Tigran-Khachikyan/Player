package com.example.player.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.player.data.db.Database;
import com.example.player.data.repo.Repository;
import com.example.player.model.Movie;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Repository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(Database.getInstance(application).getMovieDao());
    }

    private MutableLiveData<List<Movie>> movies = new MutableLiveData<>();

    LiveData<List<Movie>> getMovies() {
        return movies;
    }

    void addMovie(Movie movie) {
        repository.addMovie(movie);
    }

    void removeMovie(int id) {
        repository.removeMovie(id);
    }

}
