package in.deepanshut041.mmovie.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import in.deepanshut041.mmovie.view.base.viewmodel.ViewModelFactory;
import in.deepanshut041.mmovie.view.main.viewmodel.MovieSearchViewModel;
import in.deepanshut041.mmovie.view.main.viewmodel.MovieListViewModel;
import in.deepanshut041.mmovie.view.mdetail.viewmodel.MovieDetailViewModel;
import in.deepanshut041.mmovie.view.search.viewmodel.SearchViewModel;

/**
 * File Description: Allows us to inject dependencies via constructor injection
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 03/25/2019
 * Modified: 03/25/2019
 */
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsMovieListViewModel(MovieListViewModel movieListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsMovieDetailViewModel(MovieDetailViewModel movieDetailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsSearchViewModel(SearchViewModel searchViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MovieSearchViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsMovieSearchViewModel(MovieSearchViewModel movieSearchViewModel);


    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);


}