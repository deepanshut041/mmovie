package in.deepanshut041.mmovie.view.main.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import in.deepanshut041.mmovie.R;
import in.deepanshut041.mmovie.data.local.entity.MovieEntity;
import in.deepanshut041.mmovie.data.remote.Status;
import in.deepanshut041.mmovie.databinding.MovieSearchFragmentBinding;
import in.deepanshut041.mmovie.view.base.BaseFragment;
import in.deepanshut041.mmovie.view.main.adapter.MovieListAdapter;
import in.deepanshut041.mmovie.view.main.callbacks.MovieFragmentCallback;
import in.deepanshut041.mmovie.view.main.callbacks.MovieListCallback;
import in.deepanshut041.mmovie.view.main.viewmodel.MovieSearchViewModel;

public class MovieSearchFragment extends BaseFragment<MovieSearchViewModel, MovieSearchFragmentBinding> implements MovieListCallback {


    @Inject
    MovieFragmentCallback movieFragmentCallback;

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
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.recyclerView.setAdapter(new MovieListAdapter(this));
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

        // Make it open from the start
        item.expandActionView();

        item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {

                movieFragmentCallback.closeSearchFragment();
                return true;
            }
        });

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchMovies(query);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    public void fetchMovies(String query){
        viewModel.getSearchMovies(query).observe(this, listResource -> {
            if(null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)){
                dataBinding.loginProgress.setVisibility(View.GONE);
            }

            if(null != listResource && (listResource.status == Status.LOADING)){
                dataBinding.loginProgress.setVisibility(View.VISIBLE);
            }

            dataBinding.setResource(listResource);

            if(null != dataBinding.recyclerView.getAdapter() && dataBinding.recyclerView.getAdapter().getItemCount() > 0){
                dataBinding.errorText.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onMovieClicked(MovieEntity movieEntity) {
        movieFragmentCallback.openMovieDetailView(movieEntity);
    }
}
