package in.deepanshut041.mmovie.data.remote.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import in.deepanshut041.mmovie.data.local.dao.MovieDao;
import in.deepanshut041.mmovie.data.local.entity.MovieEntity;
import in.deepanshut041.mmovie.data.remote.ApiService;
import in.deepanshut041.mmovie.data.remote.NetworkBoundResource;
import in.deepanshut041.mmovie.data.remote.Resource;
import in.deepanshut041.mmovie.data.remote.model.PopularMovieResponse;
import retrofit2.Call;

/**
 * File Description:
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 27/03/2019$
 * Modified: 27/03/2019$
 */
public class MovieRepository {
    private final MovieDao movieDao;
    private final ApiService apiService;

    @Inject
    MovieRepository(MovieDao dao, ApiService service) {
        this.movieDao = dao;
        this.apiService = service;
    }

    /**
     * This method fetches the popular movies from the service.
     * Once the fetching is done the data is cached to local db so that the app can even work offline
     * @param pageNo index indicating how far back
     * @return List of movies
     */
    public LiveData<Resource<List<MovieEntity>>> loadPopularMovies(int pageNo) {
        return new NetworkBoundResource<List<MovieEntity>, PopularMovieResponse>() {

            @Override
            protected void saveCallResult(PopularMovieResponse item) {
                if(null != item)
                    movieDao.saveMovies(item.getPopularMovies());
            }

            @NonNull
            @Override
            protected LiveData<List<MovieEntity>> loadFromDb() {
                return movieDao.loadPopularMovies();
            }

            @NonNull
            @Override
            protected Call<PopularMovieResponse> createCall() {
                return apiService.loadPopularMovies(pageNo);
            }
        }.getAsLiveData();
    }



}
