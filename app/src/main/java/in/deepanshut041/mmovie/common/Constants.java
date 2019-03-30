package in.deepanshut041.mmovie.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.StringDef;

/**
 * File Description:
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 27/03/2019$
 * Modified: 27/03/2019$
 */
public class Constants {

    public static final String LANGUAGE_KEY = "language_key";

    public static final String LANGUAGE_ENGLISH = "en";
    public static final String LANGUAGE_GERMAN = "de";
    public static final String LANGUAGE_HINDI = "hi";
    public static final String MOVIE_ID = "movie_id";
    public static final String SEARCH_MOVIE = "Search Movies ...";

    public static final String LOCALE_EN = "en-US";


    @StringDef({LANGUAGE_ENGLISH, LANGUAGE_GERMAN, LANGUAGE_HINDI})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LanguageCodes {}
    private Constants(){
        // Private constructor to hide the implicit one
    }
}
