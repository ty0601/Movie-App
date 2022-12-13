package com.example.pd2_movie_app.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ResultPopular {

    public List<Result> results = new ArrayList<>();

    public static class Result implements Parcelable {

        private String title;
        private String release_date;
        private String vote_average;
        private String poster_path;
        private String overview;
        private String backdrop_path;
        private String original_language;

        protected Result(Parcel in) {
            title = in.readString();
            release_date = in.readString();
            vote_average = in.readString();
            poster_path = in.readString();
            overview = in.readString();
            backdrop_path = in.readString();
            original_language = in.readString();
        }

        public static final Creator<Result> CREATOR = new Creator<Result>() {
            @Override
            public Result createFromParcel(Parcel in) {
                return new Result(in);
            }

            @Override
            public Result[] newArray(int size) {
                return new Result[size];
            }
        };

        public String getTitle() {
            return title;
        }

        public String getRelease_date() {
            return release_date;
        }

        public String getVote_average() {
            return vote_average;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public String getOverview() {
            return overview;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public String getOriginal_language() {
            return original_language;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeString(release_date);
            dest.writeString(vote_average);
            dest.writeString(poster_path);
            dest.writeString(overview);
            dest.writeString(backdrop_path);
            dest.writeString(original_language);
        }
    }
}

