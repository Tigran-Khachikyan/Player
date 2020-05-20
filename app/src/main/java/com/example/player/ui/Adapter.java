package com.example.player.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.player.R;
import com.example.player.model.Movie;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private List<Movie> movies;

    void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    private Context context;
    private OnHolderClickListener holderClickListener;


    Adapter(Context context, List<Movie> movies, OnHolderClickListener holderClickListener) {
        this.movies = movies;
        this.context = context;
        this.holderClickListener = holderClickListener;
    }

    class Holder extends RecyclerView.ViewHolder {
        Holder(@NonNull View itemView) {
            super(itemView);
        }

        private AppCompatTextView tvName = itemView.findViewById(R.id.tv_name);
        private AppCompatTextView tvUrl = itemView.findViewById(R.id.tv_webUrl);
        private AppCompatTextView tvId = itemView.findViewById(R.id.tv_id);

        void bind(final Movie movie) {
            tvName.setText(movie.getFileName());
            tvUrl.setText(movie.getWebUrl());
            tvId.setText(movie.getId());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holderClickListener.openMovie(movie);
                }
            });
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_movie, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if (movies != null) {
            Movie movie = movies.get(position);
            holder.bind(movie);
        }
    }

    @Override
    public int getItemCount() {
        return movies != null ? movies.size() : 0;
    }
}
