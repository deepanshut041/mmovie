package in.deepanshut041.mmovie.data.remote;

import in.deepanshut041.mmovie.data.remote.model.MovieResponse;
import in.deepanshut041.mmovie.data.remote.model.PopularMovieResponse;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * File Description:
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 27/03/2019$
 * Modified: 27/03/2019$
 */
public interface ApiService {
    @GET("3/discover/movie")
    Observable<PopularMovieResponse> loadPopularMovies(@Query("page") int page);

    @GET("3/movie/{id}")
    Single<MovieResponse> loadMovie(@Path("id") long id);
}
