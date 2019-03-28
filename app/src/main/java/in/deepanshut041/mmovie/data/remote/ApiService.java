package in.deepanshut041.mmovie.data.remote;

import in.deepanshut041.mmovie.data.remote.model.PopularMovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * File Description:
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 27/03/2019$
 * Modified: 27/03/2019$
 */
public interface ApiService {
    @GET("3/movie/popular")
    Call<PopularMovieResponse> loadPopularMovies(@Query("page") int page);
}
