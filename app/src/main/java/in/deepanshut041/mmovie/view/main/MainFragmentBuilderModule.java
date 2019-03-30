package in.deepanshut041.mmovie.view.main;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import in.deepanshut041.mmovie.view.main.callbacks.MovieFragmentCallback;
import in.deepanshut041.mmovie.view.main.fragment.MovieListFragment;


/**
 * File Description: This builder provides android injector service to fragments.
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 03/25/2019
 * Modified: 03/25/2019
 */
@Module
public abstract class MainFragmentBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract MovieListFragment contributeMainFragment();

    @Binds
    @SuppressWarnings("unused")
    abstract MovieFragmentCallback movieFragmentCallback(MainActivity mainActivity);

}
