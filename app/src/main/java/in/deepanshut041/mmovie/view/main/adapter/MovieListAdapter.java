package in.deepanshut041.mmovie.view.main.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.deepanshut041.mmovie.data.local.entity.MovieEntity;
import in.deepanshut041.mmovie.databinding.ItemArticleListBinding;
import in.deepanshut041.mmovie.view.base.BaseAdapter;
import in.deepanshut041.mmovie.view.main.callbacks.MovieListCallback;

/**
 * File Description:
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 27/03/2019$
 * Modified: 27/03/2019$
 */
public class MovieListAdapter extends BaseAdapter<MovieListAdapter.MovieViewHolder, MovieEntity> {

    private List<MovieEntity> movieEntities;
    private final MovieListCallback movieListCallback;
    public MovieListAdapter(MovieListCallback movieListCallback) {
        this.movieListCallback = movieListCallback;
        this.movieEntities = new ArrayList<>();
    }

    @Override
    public void setData(List<MovieEntity> data) {
        this.movieEntities = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return MovieViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, movieListCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.onBind(movieEntities.get(i));
    }

    @Override
    public int getItemCount() {
        return movieEntities.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private static MovieViewHolder create(LayoutInflater inflater, ViewGroup parent, MovieListCallback movieListCallback) {
            ItemArticleListBinding itemMovieListBinding = ItemArticleListBinding.inflate(inflater, parent, false);
            return new MovieViewHolder(itemMovieListBinding, movieListCallback);
        }

        final ItemArticleListBinding binding;

        MovieViewHolder(ItemArticleListBinding binding, MovieListCallback movieListCallback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    movieListCallback.onMovieClicked(binding.getMovie()));
        }

        void onBind(MovieEntity movieEntity) {
            binding.setMovie(movieEntity);
            binding.executePendingBindings();
            binding.imageView.setImageURI("https://image.tmdb.org/t/p/w500" + movieEntity.getPosterPath());
        }



    }
}
