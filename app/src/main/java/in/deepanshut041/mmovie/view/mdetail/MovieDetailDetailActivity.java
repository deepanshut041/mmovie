package in.deepanshut041.mmovie.view.mdetail;

import android.os.Bundle;

import in.deepanshut041.mmovie.R;
import in.deepanshut041.mmovie.databinding.ActivityMovieDetailBinding;
import in.deepanshut041.mmovie.util.FragmentUtils;
import in.deepanshut041.mmovie.view.base.BaseActivity;
import in.deepanshut041.mmovie.view.mdetail.callbacks.MovieDetailFragmentCallback;
import in.deepanshut041.mmovie.view.mdetail.fragment.MovieDetailFragment;

import static in.deepanshut041.mmovie.util.FragmentUtils.TRANSITION_NONE;

public class MovieDetailDetailActivity extends BaseActivity<ActivityMovieDetailBinding> implements MovieDetailFragmentCallback {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtils.replaceFragment(this, MovieDetailFragment.newInstance(getIntent().getExtras()),
                R.id.fragment_container, false, TRANSITION_NONE);
        if(null != getSupportActionBar())
            getSupportActionBar().setTitle(getString(R.string.app_name));

    }


}
