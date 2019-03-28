package in.deepanshut041.mmovie.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.util.Log;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.List;

import javax.inject.Inject;

import in.deepanshut041.mmovie.data.local.entity.MovieEntity;
import in.deepanshut041.mmovie.data.remote.Resource;
import in.deepanshut041.mmovie.data.remote.model.PopularMovieResponse;
import in.deepanshut041.mmovie.data.remote.repository.MovieRepository;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * File Description: Main View Model
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 27/03/2019
 * Modified: 27/03/2019
 */
public class MovieListViewModel extends ViewModel {
    private final MovieRepository movieRepository;
    private final MediatorLiveData<Resource<List<MovieEntity>>> result = new MediatorLiveData<>();
    private CompositeDisposable disposable;

    @Inject
    public MovieListViewModel(MovieRepository movieRepository) {
        disposable = new CompositeDisposable();
        this.movieRepository = movieRepository;
        
        Disposable internetDiposable = ReactiveNetwork
            .observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    connectivity -> {
                        if(connectivity){
                            fetchMovies(true);
                        } else {
                            fetchMovies(false);
                        }
                    }
            );
        disposable.add(internetDiposable);
    }

    public final LiveData<Resource<List<MovieEntity>>> getPopularMovies() {
        return result;
    }

    private void fetchMovies(boolean isInternet){
        result.setValue(Resource.loading(null));
        LiveData<List<MovieEntity>> dbSource = movieRepository.loadMoviesFromDb();
        if(shouldFetch() && isInternet){
            fetchFromNetwork(dbSource);
        }else {
            result.addSource(dbSource, newData -> {
                if(null != newData)
                    result.setValue(Resource.success(newData)) ;
            });
        }
    }

    @SuppressLint("CheckResult")
    private void fetchFromNetwork(final LiveData<List<MovieEntity>> dbSource){
        result.addSource(dbSource, newData -> result.setValue(Resource.loading(newData)));
        movieRepository.loadPopularMovies().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new Observer<PopularMovieResponse>() {
                @Override
                public void onSubscribe(Disposable d) {
                    disposable.add(d);
                }

                @Override
                public void onNext(PopularMovieResponse popularMovieResponse) {
                    if(null != popularMovieResponse.getPopularMovies())
                        saveResult(popularMovieResponse.getPopularMovies());

                }

                @Override
                public void onError(Throwable e) {
                    result.removeSource(dbSource);
                    result.addSource(dbSource, newData -> result.setValue(Resource.error(movieRepository.getCustomErrorMessage(e), newData)));

                }

                @Override
                public void onComplete() {
                    result.removeSource(dbSource);
                    result.addSource(movieRepository.loadMoviesFromDb(), newData -> result.setValue(Resource.success(newData)));
                }
            });

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }

    @MainThread
    private boolean shouldFetch() {
        return true;
    }

    @SuppressLint("StaticFieldLeak")
    @MainThread
    private void saveResult(List<MovieEntity> response) {
        new AsyncTask<Void, Void, Void>() {

            @SuppressLint("WrongThread")
            @Override
            protected Void doInBackground(Void... voids) {
                movieRepository.saveMoviesToDb(response);
                return null;
            }
        }.execute();
    }

}
