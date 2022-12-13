package com.example.pd2_movie_app.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiPopular {

    private static ApiPopular mApiPopular = new ApiPopular();
    private MovieService mPopularService;

    private ApiPopular() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mPopularService = retrofit.create(MovieService.class);

    }

    public static ApiPopular getApiPopular() {
        return mApiPopular;
    }

    public MovieService getMovieService() {
        return mPopularService;
    }

}
