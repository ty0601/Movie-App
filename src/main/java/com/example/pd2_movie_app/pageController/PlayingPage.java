package com.example.pd2_movie_app.pageController;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pd2_movie_app.MoreActivity;
import com.example.pd2_movie_app.R;
import com.example.pd2_movie_app.adapter.AdapterNowPlaying;
import com.example.pd2_movie_app.model.ResultNowPlaying;
import com.example.pd2_movie_app.retrofit.ApiNowPlaying;
import com.example.pd2_movie_app.retrofit.MovieService;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class PlayingPage extends Fragment implements AdapterNowPlaying.OnNowPlayingListener {
    private static final String TAG = "PlayingPage";

    private AdapterNowPlaying mAdapterNowPlaying;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private List<ResultNowPlaying.Result> mPlaying;

    public PlayingPage(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playing_page,container,false);

        mPlaying = new ArrayList<>();
        mRecyclerView = view.findViewById(R.id.recycle_view_now_playing);
        mAdapterNowPlaying = new AdapterNowPlaying(mContext,this);

        mRecyclerView.setAdapter(mAdapterNowPlaying);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);

        getNowPlayingMovie();

        return view;
    }

    public void getNowPlayingMovie() {

        MovieService movieService;
        movieService = ApiNowPlaying.getApiNowPlaying().getMovieService();
        Call<ResultNowPlaying> playing = movieService.getNowPlaying();

        playing.enqueue(new Callback<ResultNowPlaying>() {
            @Override
            public void onResponse(Call<ResultNowPlaying> call, Response<ResultNowPlaying> response) {
                if(response.isSuccessful()){
                    List<ResultNowPlaying.Result> resultList = response.body().results;
                    mAdapterNowPlaying.setItem(resultList);
                    mPlaying = resultList;
                }
            }

            @Override
            public void onFailure(Call<ResultNowPlaying> call, Throwable t) {
                Log.d(TAG, "onFailure: Now playing failed");
            }
        });
    }

    @Override
    public void OnPlayingClick(int position) {
        Intent intent = new Intent(getActivity(), MoreActivity.class);
        if(mPlaying.size()>0) {
            intent.putExtra("selected_playing",mPlaying.get(position));
            Log.d(TAG, "OnPlayingClick: mplaying : " + mPlaying.get(position));
        }
        startActivity(intent);
    }
}
