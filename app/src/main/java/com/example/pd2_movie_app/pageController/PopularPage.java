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
import com.example.pd2_movie_app.adapter.AdapterPopular;
import com.example.pd2_movie_app.model.ResultPopular;
import com.example.pd2_movie_app.retrofit.ApiPopular;
import com.example.pd2_movie_app.retrofit.MovieService;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class PopularPage extends Fragment implements AdapterPopular.OnPopularListener {
    private static final String TAG = "PopularPage";

    private AdapterPopular adapterPopular;
    private Context context;
    private List<ResultPopular.Result> mPopular;

    public PopularPage(Context mContext) {
        this.context = mContext;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: popular recycleView page called");
        View view = inflater.inflate(R.layout.poppular_page, container, false);

        mPopular = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_popular);
        adapterPopular = new AdapterPopular(context, this);

        recyclerView.setAdapter(adapterPopular);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        getPopularMovie();

        return view;
    }

    public void getPopularMovie() {
        //declare a service
        MovieService popularService;
        //get connect to the service by ApiPopular
        popularService = ApiPopular.getApiPopular().getMovieService();
        //connect to Call
        Call<ResultPopular> popular = popularService.getPopular();

        popular.enqueue(new Callback<ResultPopular>() {
            @Override
            public void onResponse(Call<ResultPopular> call, Response<ResultPopular> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: successful.");
                    ResultPopular resultPopular = response.body();
                    List<ResultPopular.Result> resultList = resultPopular.results;
                    adapterPopular.setItem(resultList);
                    mPopular = resultList;
                }
            }

            @Override
            public void onFailure(Call<ResultPopular> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed");
            }
        });
    }


    @Override
    public void onPopularClick(int position) {
        Intent intent = new Intent(getActivity(), MoreActivity.class);
        if(mPopular.size() > 0){
            intent.putExtra("selected_popular",mPopular.get(position));
        }
        startActivity(intent);
    }
}


