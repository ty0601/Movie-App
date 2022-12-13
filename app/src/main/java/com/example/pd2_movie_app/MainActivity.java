package com.example.pd2_movie_app;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pd2_movie_app.adapter.AdapterPopular;
import com.example.pd2_movie_app.pageController.PlayingPage;
import com.example.pd2_movie_app.pageController.PopularPage;
import com.example.pd2_movie_app.pageController.TopRatePage;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    public int page;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set bottomNavigationㄆ
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_btm_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new PopularPage(this)).commit();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()){
                case R.id.nav_popular:
                    fragment = new PopularPage(context);
                    break;
                case R.id.nav_top_rate:
                    fragment = new TopRatePage(context);
                    break;
                case R.id.nav_playing:
                    fragment = new PlayingPage(context);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();
            return true;
        }
    };



}