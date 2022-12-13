package com.example.pd2_movie_app.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiTopRate {

    private static ApiTopRate mApiTopRate = new ApiTopRate();

    private MovieService mMovieService;

    private ApiTopRate() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mMovieService = retrofit.create(MovieService.class);
    }

    public static ApiTopRate getApiTopRate(){
        return mApiTopRate;
    }

    public MovieService getMovieService() {
        return mMovieService;
    }

}
