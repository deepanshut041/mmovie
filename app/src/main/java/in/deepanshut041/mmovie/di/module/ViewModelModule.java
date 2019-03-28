package in.deepanshut041.mmovie.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import in.deepanshut041.mmovie.viewmodel.MovieListViewModel;
import in.deepanshut041.mmovie.viewmodel.ViewModelFactory;

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
    abstract ViewModel bindsMainDataViewModel(MovieListViewModel movieListViewModel);


    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}