package com.example.pd2_movie_app.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiNowPlaying {

    private static ApiNowPlaying mApiNowPlaying = new ApiNowPlaying();

    private MovieService mMovieService;

    private ApiNowPlaying() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mMovieService = retrofit.create(MovieService.class);
    }

    public MovieService getMovieService(){
        return mMovieService;
    }

    public static ApiNowPlaying getApiNowPlaying() {
        return mApiNowPlaying;
    }
}
