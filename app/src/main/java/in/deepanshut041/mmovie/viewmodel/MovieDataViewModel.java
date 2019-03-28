package in.deepanshut041.mmovie.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import in.deepanshut041.mmovie.data.local.entity.MovieEntity;
import in.deepanshut041.mmovie.data.remote.Resource;
import in.deepanshut041.mmovie.data.remote.repository.MovieRepository;

/**
 * File Description: Main View Model
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 27/03/2019
 * Modified: 27/03/2019
 */
public class MovieDataViewModel extends ViewModel {

    private final LiveData<Resource<List<MovieEntity>>> popularMovies;

    @Inject
    public MovieDataViewModel(MovieRepository movieRepository) {
        popularMovies = movieRepository.loadPopularMovies(1);
    }

    public LiveData<Resource<List<MovieEntity>>> getPopularMovies() {
        return popularMovies;
    }
}
