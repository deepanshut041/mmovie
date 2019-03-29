package in.deepanshut041.mmovie.view.mdetail.viewmodel;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.io.IOException;

import javax.inject.Inject;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import in.deepanshut041.mmovie.data.remote.ApiConstants;
import in.deepanshut041.mmovie.data.remote.Resource;
import in.deepanshut041.mmovie.data.remote.model.MovieResponse;
import in.deepanshut041.mmovie.data.remote.repository.MovieRepository;
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
public class MovieDetailViewModel extends ViewModel {
    private final MovieRepository movieRepository;
    private final MediatorLiveData<Resource<MovieResponse>> result = new MediatorLiveData<>();
    private CompositeDisposable disposable;
    private long movieId;

    @Inject
    public MovieDetailViewModel(MovieRepository movieRepository) {
        disposable = new CompositeDisposable();
        this.movieRepository = movieRepository;
    }

    public final LiveData<Resource<MovieResponse>> getMovie(long id)
    {
        this.movieId = id;
        Disposable internetDiposable = ReactiveNetwork
                .observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::fetchFromNetwork
                );
        disposable.add(internetDiposable);
        return result;
    }


    private void fetchFromNetwork(boolean isInternet){
        if(shouldFetch()&&isInternet){
            fetchMovie();
        } else {
            result.setValue(Resource.error(ApiConstants.getCustomErrorMessage(new IOException()), null));
        }
    }

    private void fetchMovie() {
        result.setValue(Resource.loading(null));
        Disposable movieDisposable = movieRepository
                .loadMovie(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    data ->{
                        result.setValue(Resource.success(data));
                    }, throwable ->{
                        result.setValue(Resource.error(ApiConstants.getCustomErrorMessage(throwable), null));
                    }
                );
        disposable.add(movieDisposable);
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
}
