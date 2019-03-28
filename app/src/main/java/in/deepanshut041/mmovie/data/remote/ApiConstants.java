package in.deepanshut041.mmovie.data.remote;

import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.net.SocketTimeoutException;

import in.deepanshut041.mmovie.AcApp;
import in.deepanshut041.mmovie.R;
import retrofit2.HttpException;

/**
 * File Description: Keep all the service related constants here.
 * Author: Deepanshu Tyagi
 * Email: deepanshut041@gmail.com
 * Created: 27/03/2019$
 * Modified: 27/03/2019$
 */
public class ApiConstants {
    public static final String BASE_URL = "https://api.themoviedb.org/";
    public static final long CONNECT_TIMEOUT = 30000;
    public static final long READ_TIMEOUT = 30000;
    public static final long WRITE_TIMEOUT = 30000;
    public static final String API_KEY = "fe56cdee4dfea0c18403e0965acfa23b";

    private ApiConstants(){
        // Private constructor to hide the implicit one
    }

    public static String getCustomErrorMessage(Throwable error){

        if (error instanceof SocketTimeoutException) {
            return AcApp.getAppContext().getString(R.string.requestTimeOutError);
        } else if (error instanceof MalformedJsonException) {
            return  AcApp.getAppContext().getString(R.string.responseMalformedJson);
        } else if (error instanceof IOException) {
            return  AcApp.getAppContext().getString(R.string.networkError);
        } else if (error instanceof HttpException) {
            return (((HttpException) error).response().message());
        } else {
            return AcApp.getAppContext().getString(R.string.unknownError);
        }

    }
}
