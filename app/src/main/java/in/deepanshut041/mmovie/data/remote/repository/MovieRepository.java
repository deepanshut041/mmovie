package in.deepanshut041.mmovie.data.remote.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import javax.inject.Inject;

import in.deepanshut041.mmovie.AcApp;
import in.deepanshut041.mmovie.R;
import in.deepanshut041.mmovie.data.local.dao.MovieDao;
import in.deepanshut041.mmovie.data.local.entity.MovieEntity;
import in.deepanshut041.mmovie.data.remote.ApiService;
import in.deepanshut041.mmovie.data.remote.Resource;
import in.deepanshut041.mmovie.data.remote.model.PopularMovieResponse;
import io.reactivex.Observable;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

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
        final MutableLiveData<Resource<List<MovieEntity>>> liveData = new MutableLiveData<>();
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

    public String getCustomErrorMessage(Throwable error){

        if (error instanceof SocketTimeoutException) {
            return AcApp.getAppContext().getString(R.string.requestTimeOutError);
        } else if (error instanceof MalformedJsonException) {
            return  AcApp.getAppContext().getString(R.string.responseMalformedJson);
        } else if (error instanceof IOException) {
            return  AcApp.getAppContext().getString(R.string.networkError);
        } else if (error instanceof HttpException) {
            return (((HttpException) error).response().message());
        } else {
            return AcApp.getAppContext().getString(R.string.unknownError);
        }

    }
}
