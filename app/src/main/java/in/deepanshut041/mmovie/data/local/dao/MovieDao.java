package in.deepanshut041.mmovie.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import in.deepanshut041.mmovie.data.local.entity.MovieEntity;

/**
 * File Description:
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 27/03/2019$
 * Modified: 27/03/2019$
 */
@Dao
public interface MovieDao {

    @Query("SELECT * FROM movies")
    LiveData<List<MovieEntity>> loadPopularMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<MovieEntity> movieEntities);
}
