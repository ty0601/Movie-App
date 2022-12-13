package com.example.pd2_movie_app.pageController;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.pd2_movie_app.adapter.AdapterTopRate;
import com.example.pd2_movie_app.model.ResultTopRate;
import com.example.pd2_movie_app.retrofit.ApiTopRate;
import com.example.pd2_movie_app.retrofit.MovieService;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class TopRatePage extends Fragment implements AdapterTopRate.OnTopRateListener {
    private static final String TAG = "TopRatePage";

    private AdapterTopRate mAdapterTopRate;
    private Context mContext;
    private List<ResultTopRate.Result> mTop;

    public TopRatePage(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_page, container, false);

        mTop = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_top_rate);
        mAdapterTopRate = new AdapterTopRate(mContext,this);

        recyclerView.setAdapter(mAdapterTopRate);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);

        getTopRateMovie();
        return view;
    }

    public void getTopRateMovie() {
        //declare a service
        MovieService popularService;
        //get connect to the service by ApiPopular
        popularService = ApiTopRate.getApiTopRate().getMovieService();
        //connect to Call
        Call<ResultTopRate> topRate = popularService.getTopRate();

        topRate.enqueue(new Callback<ResultTopRate>() {
            @Override
            public void onResponse(Call<ResultTopRate> call, Response<ResultTopRate> response) {
                if(response.isSuccessful()){
                    ResultTopRate resultTopRate = response.body();
                    List<ResultTopRate.Result> topRateList = resultTopRate.results;
                    mAdapterTopRate.setItem(topRateList);
                    mTop = topRateList;
                }
            }

            @Override
            public void onFailure(Call<ResultTopRate> call, Throwable t) {
                Log.d(TAG, "onFailure: Top Rate Failed getting API");
            }
        });


    }

    @Override
    public void onTopClick(int position) {
        Intent intent = new Intent(getActivity(), MoreActivity.class);
        if(mTop.size() > 0){
            intent.putExtra("selected_top", mTop.get(position));
        }
        startActivity(intent);
    }
}
