package in.deepanshut041.mmovie.view.main.viewmodel;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import in.deepanshut041.mmovie.data.local.entity.MovieEntity;
import in.deepanshut041.mmovie.data.remote.ApiConstants;
import in.deepanshut041.mmovie.data.remote.Resource;
import in.deepanshut041.mmovie.data.remote.repository.MovieRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieSearchViewModel extends ViewModel {
    private final MovieRepository movieRepository;
    private final MediatorLiveData<Resource<List<MovieEntity>>> result = new MediatorLiveData<>();
    private CompositeDisposable disposable;
    private String query;

    @Inject
    MovieSearchViewModel(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
        this.disposable = new CompositeDisposable();
    }

    public final LiveData<Resource<List<MovieEntity>>> getSearchMovies(String query) {
        this.query = query;
        Disposable internetDisposable = ReactiveNetwork
            .observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    this::fetchFromNetwork
            );
    disposable.add(internetDisposable);
        return result;
    }

    private void fetchFromNetwork(Boolean isInternet) {
        if(shouldFetch()&&isInternet){
            fetchMovie();
        } else {
            result.setValue(Resource.error(ApiConstants.getCustomErrorMessage(new IOException()), null));
        }
    }

    private void fetchMovie() {
        result.setValue(Resource.loading(null));
        Disposable movieDisposable = movieRepository
            .searchMovies(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                data -> {
                    if (data.getPopularMovies().size() == 0){
                        result.setValue(Resource.error("No movie found", null));
                    } else {
                        result.setValue(Resource.success(data.getPopularMovies()));
                    }

                },
                throwable -> result.setValue(Resource.error(ApiConstants.getCustomErrorMessage(throwable), null)));
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
