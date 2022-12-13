package com.example.pd2_movie_app;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.pd2_movie_app.model.ResultNowPlaying;
import com.example.pd2_movie_app.model.ResultPopular;
import com.example.pd2_movie_app.model.ResultTopRate;

public class MoreActivity extends AppCompatActivity {
    private static final String TAG = "MoreActivity";

    //ui
    private ImageView backgroundPath;
    private TextView title,date,rate,overview;

    //var
    private ResultNowPlaying.Result playingMovie;
    private ResultTopRate.Result topMovie;
    private ResultPopular.Result popularMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        title = findViewById(R.id.more_title);
        date = findViewById(R.id.more_date);
        rate = findViewById(R.id.more_rate);
        overview = findViewById(R.id.more_overview);
        backgroundPath = findViewById(R.id.back_path);

        if(getIntent().hasExtra("selected_playing")){
            playingMovie = getIntent().getParcelableExtra("selected_playing");
            setPlayingDetail();
        }else if(getIntent().hasExtra("selected_top")){
            topMovie = getIntent().getParcelableExtra("selected_top");
            setTopDetail();
        }else if(getIntent().hasExtra("selected_popular")){
            popularMovie = getIntent().getParcelableExtra("selected_popular");
            setPopularDetail();
        }
    }

    public void back(View view) {
        finish();
    }

    private void setPlayingDetail(){
        Glide.with(this).asBitmap()
                .load("https://image.tmdb.org/t/p/w500"+ playingMovie.getBackdrop_path())
                .into(backgroundPath);

        title.setText("Title : " + playingMovie.getTitle());
        date.setText("Release date : " + playingMovie.getRelease_date());
        rate.setText("Rate : " + playingMovie.getVote_average());
        overview.setText("Overview : " + playingMovie.getOverview());
    }

    private void setTopDetail(){
        Glide.with(this).asBitmap()
                .load("https://image.tmdb.org/t/p/w500"+ topMovie.getBackdrop_path())
                .into(backgroundPath);

        title.setText("Title : " + topMovie.getTitle());
        date.setText("Release date : " + topMovie.getRelease_date());
        rate.setText("Rate : " + topMovie.getVote_average());
        overview.setText("Overview : " + topMovie.getOverview());
    }

    private void setPopularDetail(){
        Glide.with(this).asBitmap()
                .load("https://image.tmdb.org/t/p/w500"+ popularMovie.getBackdrop_path())
                .into(backgroundPath);

        title.setText("Title : " + popularMovie.getTitle());
        date.setText("Release date : " + popularMovie.getRelease_date());
        rate.setText("Rate : " + popularMovie.getVote_average());
        overview.setText("Overview : " + popularMovie.getOverview());
    }
}