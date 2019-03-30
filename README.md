## Movie Application
[![Build Status](https://travis-ci.com/deepanshut041/mmovie.svg?branch=master)](https://travis-ci.com/deepanshut041/mmovie)

##Download latest apk

<a href="https://github.com/deepanshut041/mmovie/releases/latest/download/app-release.apk"><img src="https://9apps-apk.ooo/wp-content/uploads/2018/11/download.png" /></a>

## Highlights

1. MVVM Architectural pattern
2. Offline Support
3. Using TravisCI to securely build and deploy a signed version of Android app.

# Application Architecture
![alt text](https://cdn-images-1.medium.com/max/1600/1*OqeNRtyjgWZzeUifrQT-NA.png)

The main advantage of using MVVM, there is no two way dependency between ViewModel and Model unlike MVP. Here the view can observe the datachanges in the viewmodel as we are using LiveData which is lifecycle aware. The viewmodel to view communication is achieved through observer pattern (basically observing the state changes of the data in the viewmodel).


## Requirements

* The app needs to connect to the real IMDB Database. Make good use of retrofit to fetch all the data.
* The data that you just downloaded should be accessible offline (Top 100 movies should be enough)
* Must be able to search for specific movies, and be able to select them to see all the necessary
details of a specific movie.
* The movie details should be able to be updated once we go from offline to online. We don’t want to go
out of the view and into the view again to get the newest info.

## API
[TheMovieDB](https://www.themoviedb.org/) - A movie lists API

1. To get movie data - api.themoviedb.org/3/search/movie?api_key=(Api Key)&language=en-US&query=(Movie Name)
2. To get movie poster - http://<i></i>image.tmdb.org/t/p/
4. There are several sizes that you can use w92, w154, w185, w342, w500, w780, and original.
3. To get popular movie data - api.themoviedb.org/3/movie/now_playing?api_key=(API Key)&language=en-US&page=(Page No)

## Library
1. RxJava2: https://github.com/amitshekhariitbhu/RxJava2-Android-Samples
2. Dagger2: https://github.com/MindorksOpenSource/android-dagger2-example
3. Retrofit: https://square.github.io/retrofit/
4. Fresco: https://github.com/facebook/fresco/
5. ReactiveNetwork: https://github.com/pwittchen/ReactiveNetwork
6. Calligraphy: https://github.com/chrisjenx/Calligraphy
7. Room: https://developer.android.com/topic/libraries/architecture/room.html