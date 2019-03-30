package in.deepanshut041.mmovie.view.main;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu, menu);
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.search).getActionView();
//        if (searchManager != null) {
//
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String query) {
//
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String newText) {
//                    return false;
//                }
//            });
//
//        }
//
//        return true;
//    }
//
//    @Override
//    public void onBackPressed() {
//        if(getFragmentManager().getBackStackEntryCount() > 0){
//            getFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//        }
//        else{
//            super.onBackPressed();
//        }
//    }
}
