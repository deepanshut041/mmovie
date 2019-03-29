package in.deepanshut041.mmovie.view.mdetail.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import in.deepanshut041.mmovie.R;
import in.deepanshut041.mmovie.data.remote.Status;
import in.deepanshut041.mmovie.databinding.FragmentDetailMovieBinding;
import in.deepanshut041.mmovie.view.base.BaseFragment;
import in.deepanshut041.mmovie.view.mdetail.viewmodel.MovieDetailViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends BaseFragment<MovieDetailViewModel, FragmentDetailMovieBinding> {

    public static MovieDetailFragment newInstance() {
        Bundle args = new Bundle();
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<MovieDetailViewModel> getViewModel() {
        return MovieDetailViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_detail_movie;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return dataBinding.getRoot();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getMovie(122).observe(this, listResource -> {
            if(null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)){
                dataBinding.loginProgress.setVisibility(View.GONE);
            }

            if(null != listResource && (listResource.status == Status.LOADING)){
                dataBinding.loginProgress.setVisibility(View.VISIBLE);
                dataBinding.movieLayout.setVisibility(View.GONE);
                dataBinding.errorText.setVisibility(View.GONE);
            }

            if(null != listResource && (listResource.status == Status.SUCCESS)  && listResource.data != null){
                dataBinding.movieLayout.setVisibility(View.VISIBLE);
                dataBinding.errorText.setVisibility(View.GONE);
                dataBinding.setMovie(listResource.data);
                if(listResource.data.getBackdropPath() != null)
                    dataBinding.videoThumbnail.setImageURI("https://image.tmdb.org/t/p/w300" + listResource.data.getBackdropPath());
            }

            if(null != listResource && (listResource.status == Status.ERROR)){
                dataBinding.movieLayout.setVisibility(View.GONE);
                dataBinding.errorText.setVisibility(View.VISIBLE);
                dataBinding.errorText.setText(listResource.getMessage());
            }
        });
    }

}
