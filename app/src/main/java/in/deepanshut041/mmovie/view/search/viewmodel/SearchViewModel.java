package in.deepanshut041.mmovie.view.search.viewmodel;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;
import in.deepanshut041.mmovie.data.remote.repository.MovieRepository;

public class SearchViewModel extends ViewModel {
    private final MovieRepository movieRepository;


    @Inject
    public SearchViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
}
