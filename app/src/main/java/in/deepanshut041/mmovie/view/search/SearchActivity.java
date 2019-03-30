package in.deepanshut041.mmovie.view.search;

import android.os.Bundle;

import in.deepanshut041.mmovie.R;
import in.deepanshut041.mmovie.databinding.ActivitySearchBinding;
import in.deepanshut041.mmovie.util.FragmentUtils;
import in.deepanshut041.mmovie.view.base.BaseActivity;
import in.deepanshut041.mmovie.view.search.adapter.SearchFragmentCallback;
import in.deepanshut041.mmovie.view.search.fragment.SearchFragment;

import static in.deepanshut041.mmovie.util.FragmentUtils.TRANSITION_NONE;

public class SearchActivity extends BaseActivity<ActivitySearchBinding> implements SearchFragmentCallback {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtils.replaceFragment(this, SearchFragment.newInstance(),
                R.id.fragment_container, false, TRANSITION_NONE);
    }


}
