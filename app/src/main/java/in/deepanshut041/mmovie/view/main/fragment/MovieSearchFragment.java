package in.deepanshut041.mmovie.view.main.fragment;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import in.deepanshut041.mmovie.R;
import in.deepanshut041.mmovie.databinding.MovieSearchFragmentBinding;
import in.deepanshut041.mmovie.view.base.BaseFragment;
import in.deepanshut041.mmovie.view.main.MainActivity;
import in.deepanshut041.mmovie.view.main.viewmodel.MovieSearchViewModel;

import static android.content.Context.SEARCH_SERVICE;

public class MovieSearchFragment extends BaseFragment<MovieSearchViewModel, MovieSearchFragmentBinding> {

    public static MovieSearchFragment newInstance() {
        return new MovieSearchFragment();
    }

    @Override
    protected Class<MovieSearchViewModel> getViewModel() {
        return MovieSearchViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.movie_search_fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        // Get the SearchView item
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getActivity().getComponentName()));

        // Make it open from the start
        item.expandActionView();

    }

}
