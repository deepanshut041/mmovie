package in.deepanshut041.mmovie.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import in.deepanshut041.mmovie.data.local.dao.MovieDao;
import in.deepanshut041.mmovie.data.local.entity.MovieEntity;

/**
 * File Description:
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 27/03/2019$
 * Modified: 27/03/2019$
 */
@Database(entities = {MovieEntity.class}, version = 2)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
}
