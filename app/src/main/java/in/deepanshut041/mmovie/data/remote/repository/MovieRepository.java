package in.deepanshut041.mmovie.data.remote.repository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import in.deepanshut041.mmovie.data.local.dao.MovieDao;
import in.deepanshut041.mmovie.data.local.entity.MovieEntity;
import in.deepanshut041.mmovie.data.remote.ApiService;
import in.deepanshut041.mmovie.data.remote.model.MovieResponse;
import in.deepanshut041.mmovie.data.remote.model.PopularMovieResponse;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    public Observable<PopularMovieResponse> loadPopularMovies() {
        Observable<PopularMovieResponse> page1 = apiService.loadPopularMovies(1).observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Observable<PopularMovieResponse> page2 = apiService.loadPopularMovies(2).observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Observable<PopularMovieResponse> page3 = apiService.loadPopularMovies(3).observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Observable<PopularMovieResponse> page4 = apiService.loadPopularMovies(4).observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Observable<PopularMovieResponse> page5 = apiService.loadPopularMovies(5).observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());


        Observable<PopularMovieResponse> pages = Observable.zip(page1, page2, page3, page4, page5,
                (result1, result2, result3, result4, result5) -> {
                    PopularMovieResponse popularMovieResponses = new PopularMovieResponse();
                    // Stream could be used for optimization(min sdk 24) issue
                    List<MovieEntity> movieEntities = result1.getPopularMovies();
                    movieEntities.addAll(result2.getPopularMovies());
                    movieEntities.addAll(result3.getPopularMovies());
                    movieEntities.addAll(result4.getPopularMovies());
                    movieEntities.addAll(result5.getPopularMovies());
                    popularMovieResponses.setPopularMovies(movieEntities);
                    return popularMovieResponses;
                }
        );
        return pages;
    }

    public LiveData<List<MovieEntity>> loadMoviesFromDb(){
        return movieDao.loadPopularMovies();
    }

    public void saveMoviesToDb(List<MovieEntity> popularMovies) {
        movieDao.saveMovies(popularMovies);
    }

    public  Single<MovieResponse> loadMovie(long id){
        return apiService.loadMovie(id);
    }

    public Single<PopularMovieResponse> searchMovies(String query){
        return apiService.searchMovies(query);
    }
}
