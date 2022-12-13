package com.example.pd2_movie_app.retrofit;

import com.example.pd2_movie_app.model.ResultNowPlaying;
import com.example.pd2_movie_app.model.ResultPopular;
import com.example.pd2_movie_app.model.ResultTopRate;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {

    @GET("/3/movie/popular?api_key=ae84f682cf50c20d864e92f56ee3c947&language=en-US&page=1")
    Call<ResultPopular> getPopular();

    @GET("/3/movie/top_rated?api_key=ae84f682cf50c20d864e92f56ee3c947&language=en-US&page=1")
    Call<ResultTopRate> getTopRate();

    @GET("/3/movie/now_playing?api_key=ae84f682cf50c20d864e92f56ee3c947&language=en-US&page=1")
    Call<ResultNowPlaying> getNowPlaying();

}
