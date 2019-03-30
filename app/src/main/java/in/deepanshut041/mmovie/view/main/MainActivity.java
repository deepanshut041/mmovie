package in.deepanshut041.mmovie.view.main;

import android.content.Intent;
import android.os.Bundle;

import in.deepanshut041.mmovie.R;
import in.deepanshut041.mmovie.common.Constants;
import in.deepanshut041.mmovie.data.local.entity.MovieEntity;
import in.deepanshut041.mmovie.databinding.ActivityMainBinding;
import in.deepanshut041.mmovie.util.FragmentUtils;
import in.deepanshut041.mmovie.view.base.BaseActivity;
import in.deepanshut041.mmovie.view.main.callbacks.MovieFragmentCallback;
import in.deepanshut041.mmovie.view.main.fragment.MovieListFragment;
import in.deepanshut041.mmovie.view.main.fragment.MovieSearchFragment;
import in.deepanshut041.mmovie.view.mdetail.MovieDetailDetailActivity;

import static in.deepanshut041.mmovie.util.FragmentUtils.TRANSITION_NONE;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements MovieFragmentCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(dataBinding.toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(R.string.app_name);
        FragmentUtils.replaceFragment(this, MovieListFragment.newInstance(), R.id.fragment_container, true, TRANSITION_NONE);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void openMovieDetailView(MovieEntity movieEntity) {
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.MOVIE_ID, movieEntity.getId());
        Intent intent = new Intent(MainActivity.this, MovieDetailDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void openSearchFragment(){
        FragmentUtils.replaceFragment(MainActivity.this, MovieSearchFragment.newInstance(), R.id.fragment_container, true, FragmentUtils.TRANSITION_FADE_IN_OUT);
    }

    @Override
    public void closeSearchFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStackImmediate();
    }
}
