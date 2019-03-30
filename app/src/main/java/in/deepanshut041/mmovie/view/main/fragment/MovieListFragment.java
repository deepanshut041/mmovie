package in.deepanshut041.mmovie.view.main.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import in.deepanshut041.mmovie.R;
import in.deepanshut041.mmovie.data.local.entity.MovieEntity;
import in.deepanshut041.mmovie.data.remote.Status;
import in.deepanshut041.mmovie.databinding.FragmentListMovieBinding;
import in.deepanshut041.mmovie.view.base.BaseFragment;
import in.deepanshut041.mmovie.view.main.adapter.MovieListAdapter;
import in.deepanshut041.mmovie.view.main.callbacks.MovieFragmentCallback;
import in.deepanshut041.mmovie.view.main.callbacks.MovieListCallback;
import in.deepanshut041.mmovie.view.main.viewmodel.MovieListViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends BaseFragment<MovieListViewModel, FragmentListMovieBinding> implements MovieListCallback {

    @Inject
    MovieFragmentCallback movieFragmentCallback;

    public static MovieListFragment newInstance() {
        Bundle args = new Bundle();
        MovieListFragment fragment = new MovieListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<MovieListViewModel> getViewModel() {
        return MovieListViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_list_movie;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.recyclerView.setAdapter(new MovieListAdapter(this));
        return dataBinding.getRoot();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getPopularMovies().observe(this, listResource -> {
            if(null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)){
                dataBinding.loginProgress.setVisibility(View.GONE);
            }

            if(null != listResource && (listResource.status == Status.LOADING)){
                dataBinding.loginProgress.setVisibility(View.VISIBLE);
            }

            dataBinding.setResource(listResource);

            // If the cached data is already showing then no need to show the error
            if(null != dataBinding.recyclerView.getAdapter() && dataBinding.recyclerView.getAdapter().getItemCount() > 0){
                dataBinding.errorText.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onMovieClicked(MovieEntity movieEntity) {
        movieFragmentCallback.openMovieDetailView(movieEntity);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                movieFragmentCallback.openSearchFragment();
                return false;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
