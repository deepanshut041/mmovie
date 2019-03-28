package in.deepanshut041.mmovie.di.builder;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import in.deepanshut041.mmovie.view.fragment.MovieListFragment;


/**
 * File Description: This builder provides android injector service to fragments.
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 03/25/2019
 * Modified: 03/25/2019
 */
@Module
public abstract class FragmentBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract MovieListFragment contributeMainFragment();
}
