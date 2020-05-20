package com.example.player.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.player.R;
import com.example.player.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton buttonAdd;
    private AppCompatButton buttonRemove;
    private AppCompatButton buttonShare;
    private AppCompatEditText editTextName;
    private AppCompatEditText editTextUrl;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private MainViewModel mainViewModel;
    private Movie currentMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        initializeViews();
        initializeListeners();
        observe();
    }

    private void initializeListeners() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainViewModel != null)
                    if (editTextName.getText() != null && editTextUrl.getText() != null) {
                        String name = editTextName.getText().toString();
                        String url = editTextUrl.getText().toString();
                        Movie movie = new Movie(name, url);
                        mainViewModel.addMovie(movie);
                    }
            }
        });
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                String text = "Id: " + currentMovie.getId() + ", Name: " + currentMovie.getFileName() + ", Url: " + currentMovie.getWebUrl();
                sendIntent.putExtra(Intent.EXTRA_TEXT, text);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, "Get new Movie!!!");
                startActivity(shareIntent);
            }
        });
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.removeMovie(currentMovie.getId());
            }
        });

        adapter = new Adapter(this, new ArrayList<Movie>(), new OnHolderClickListener() {
            @Override
            public void openMovie(Movie movie) {
                currentMovie = movie;
                editTextName.setText(movie.getFileName());
                editTextUrl.setText(movie.getWebUrl());
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initializeViews() {

        buttonAdd = findViewById(R.id.btn_add);
        buttonRemove = findViewById(R.id.btn_remove);
        buttonShare = findViewById(R.id.btn_share);
        editTextName = findViewById(R.id.et_name);
        editTextUrl = findViewById(R.id.et_url);
        recyclerView = findViewById(R.id.recViewMovies);
        recyclerView.setHasFixedSize(true);
    }

    private void observe() {
        mainViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.setMovies(movies);
            }
        });
    }

}
