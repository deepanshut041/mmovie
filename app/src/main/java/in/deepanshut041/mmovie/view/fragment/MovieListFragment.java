package in.deepanshut041.mmovie.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.deepanshut041.mmovie.R;
import in.deepanshut041.mmovie.data.remote.Status;
import in.deepanshut041.mmovie.databinding.FragmentListMovieBinding;
import in.deepanshut041.mmovie.view.adapter.MovieListAdapter;
import in.deepanshut041.mmovie.view.base.BaseFragment;
import in.deepanshut041.mmovie.viewmodel.MovieListViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends BaseFragment<MovieListViewModel, FragmentListMovieBinding> {

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
        dataBinding.recyclerView.setAdapter(new MovieListAdapter());
        return dataBinding.getRoot();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.getPopularMovies().observe(this, listResource -> {
            if(null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)){
                dataBinding.loginProgress.setVisibility(View.GONE);
            }

            dataBinding.setResource(listResource);

            // If the cached data is already showing then no need to show the error
            if(null != dataBinding.recyclerView.getAdapter() && dataBinding.recyclerView.getAdapter().getItemCount() > 0){
                dataBinding.errorText.setVisibility(View.GONE);
            }
        });
    }
}
