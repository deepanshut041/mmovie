package in.deepanshut041.mmovie.view.main.callbacks;

import androidx.appcompat.widget.Toolbar;
import in.deepanshut041.mmovie.data.local.entity.MovieEntity;

public interface MovieFragmentCallback {
    void openMovieDetailView(MovieEntity movieEntity);
    void openSearchFragment();
}
