package com.example.pd2_movie_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pd2_movie_app.R;
import com.example.pd2_movie_app.model.ResultPopular;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterPopular extends RecyclerView.Adapter<AdapterPopular.ViewHolderPopular> {

    private List<ResultPopular.Result> resultPopularsList = new ArrayList<>();
    private Context context;
    private OnPopularListener onPopularListener;
    private String overview;

    public AdapterPopular(Context context) {
        this.context = context;
    }

    public AdapterPopular(Context context, OnPopularListener onPopularListener) {
        this.context = context;
        this.onPopularListener = onPopularListener;
    }

    //initial the movie
    public void setItem(List<ResultPopular.Result> resultPopulars) {
        this.resultPopularsList = resultPopulars;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolderPopular onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        ViewHolderPopular holder = new ViewHolderPopular(view, onPopularListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderPopular holder, int position) {
        ResultPopular.Result resultPopular = resultPopularsList.get(position);
        holder.title.setText(resultPopular.getTitle());
        holder.date.setText(resultPopular.getRelease_date());
        holder.rate.setText(resultPopular.getVote_average());
        Glide.with(context).asBitmap()
                .load("https://image.tmdb.org/t/p/w500" + resultPopular.getPoster_path())
                .into(holder.movie_img);
        overview = resultPopular.getOverview();
    }

    @Override
    public int getItemCount() {
        return resultPopularsList.size();
    }

    public String getOverview() {
        return overview;
    }

    public interface OnPopularListener {
        void onPopularClick(int position);
    }

    public static class ViewHolderPopular extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, date, rate;
        ImageView movie_img;
        OnPopularListener onPopularListener;

        public ViewHolderPopular(@NonNull @NotNull View itemView, OnPopularListener onPopularListener) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title_text);
            date = itemView.findViewById(R.id.release_date_text);
            rate = itemView.findViewById(R.id.movie_rate_text);
            movie_img = itemView.findViewById(R.id.movie_image);
            this.onPopularListener = onPopularListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onPopularListener.onPopularClick(getPosition());
        }

    }
}
