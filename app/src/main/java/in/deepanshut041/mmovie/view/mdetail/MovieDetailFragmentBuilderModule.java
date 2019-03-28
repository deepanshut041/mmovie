package in.deepanshut041.mmovie.view.mdetail;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import in.deepanshut041.mmovie.view.mdetail.fragment.MovieDetailFragment;


/**
 * File Description: This builder provides android injector service to fragments.
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 03/25/2019
 * Modified: 03/25/2019
 */
@Module
public abstract class MovieDetailFragmentBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract MovieDetailFragment contributeMovieDetailFragment();

}
