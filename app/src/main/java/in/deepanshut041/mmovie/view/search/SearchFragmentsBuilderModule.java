package in.deepanshut041.mmovie.view.search;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import in.deepanshut041.mmovie.view.search.adapter.SearchFragmentCallback;
import in.deepanshut041.mmovie.view.search.fragment.SearchFragment;

@Module
public abstract class SearchFragmentsBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract SearchFragment contributeSearchFragment();

    @Binds
    @SuppressWarnings("unused")
    abstract SearchFragmentCallback searchFragmentCallback(SearchActivity searchActivity);

}
