package in.deepanshut041.mmovie.view.search.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import in.deepanshut041.mmovie.R;
import in.deepanshut041.mmovie.databinding.SearchFragmentBinding;
import in.deepanshut041.mmovie.view.base.BaseFragment;
import in.deepanshut041.mmovie.view.search.adapter.SearchFragmentCallback;
import in.deepanshut041.mmovie.view.search.viewmodel.SearchViewModel;

public class SearchFragment extends BaseFragment<SearchViewModel, SearchFragmentBinding> {

    @Inject
    SearchFragmentCallback searchFragmentCallback;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(null);
        return fragment;
    }

    public Class<SearchViewModel> getViewModel() {
        return SearchViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.search_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        backPressed();
    }

    private void backPressed(){
        dataBinding.toolbar.setNavigationOnClickListener(v -> searchFragmentCallback.onBackPressed());
    }

}
