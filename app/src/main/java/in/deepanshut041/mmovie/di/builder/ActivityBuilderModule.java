package in.deepanshut041.mmovie.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import in.deepanshut041.mmovie.view.main.MainActivity;
import in.deepanshut041.mmovie.view.main.MainFragmentBuilderModule;
import in.deepanshut041.mmovie.view.mdetail.MovieDetailDetailActivity;
import in.deepanshut041.mmovie.view.mdetail.MovieDetailFragmentBuilderModule;

/**
 * File Description: The module which provides the android injection service to Activities.
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 03/25/2019
 * Modified: 03/25/2019
 */

@Module
public abstract class ActivityBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector(modules = MainFragmentBuilderModule.class)
    abstract MainActivity mainActivity();

    @SuppressWarnings("unused")
    @ContributesAndroidInjector(modules = MovieDetailFragmentBuilderModule.class)
    abstract MovieDetailDetailActivity movieDetailActivity();

}