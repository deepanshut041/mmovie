package in.deepanshut041.mmovie.data.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.deepanshut041.mmovie.data.local.entity.MovieEntity;

/**
 * File Description:
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 27/03/2019$
 * Modified: 27/03/2019$
 */
public class PopularMovieResponse {
    @SerializedName("results")
    private List<MovieEntity> popularMovies;

    public PopularMovieResponse() {
    }

    public List<MovieEntity> getPopularMovies() {
        return popularMovies;
    }

    @SuppressWarnings("unused")
    public void setPopularMovies(List<MovieEntity> popularMovies) {
        this.popularMovies = popularMovies;
    }
}
