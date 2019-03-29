package in.deepanshut041.mmovie.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import in.deepanshut041.mmovie.R;
import in.deepanshut041.mmovie.common.Constants;
import in.deepanshut041.mmovie.data.local.entity.MovieEntity;
import in.deepanshut041.mmovie.databinding.ActivityMainBinding;
import in.deepanshut041.mmovie.util.FragmentUtils;
import in.deepanshut041.mmovie.view.base.BaseActivity;
import in.deepanshut041.mmovie.view.main.callbacks.MovieFragmentCallback;
import in.deepanshut041.mmovie.view.main.fragment.MovieListFragment;
import in.deepanshut041.mmovie.view.mdetail.MovieDetailDetailActivity;

import static in.deepanshut041.mmovie.util.FragmentUtils.TRANSITION_NONE;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements MovieFragmentCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = dataBinding.toolbar;
        setSupportActionBar(toolbar);
        FragmentUtils.replaceFragment(this, MovieListFragment.newInstance(), R.id.fragment_container, false, TRANSITION_NONE);
        if(null != getSupportActionBar())
            getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
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
}
