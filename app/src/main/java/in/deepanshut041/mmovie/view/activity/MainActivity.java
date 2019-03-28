package in.deepanshut041.mmovie.view.activity;

import android.os.Bundle;
import android.view.MenuItem;

import in.deepanshut041.mmovie.R;
import in.deepanshut041.mmovie.databinding.ActivityMainBinding;
import in.deepanshut041.mmovie.util.FragmentUtils;
import in.deepanshut041.mmovie.view.base.BaseActivity;
import in.deepanshut041.mmovie.view.fragment.MovieListFragment;

import static in.deepanshut041.mmovie.util.FragmentUtils.TRANSITION_NONE;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
}
