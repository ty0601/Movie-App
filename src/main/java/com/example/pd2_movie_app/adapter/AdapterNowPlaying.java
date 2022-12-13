package com.example.pd2_movie_app.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.pd2_movie_app.R;
import com.example.pd2_movie_app.model.ResultNowPlaying;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterNowPlaying extends RecyclerView.Adapter<AdapterNowPlaying.ViewHolderNowPlaying>{

    private List<ResultNowPlaying.Result> mNowPlayingList = new ArrayList<>();
    private Context mContext;
    private OnNowPlayingListener mOnNowPlayingListener;


    public AdapterNowPlaying(Context mContext , OnNowPlayingListener onNowPlayingListener) {
        this.mContext = mContext;
        this.mOnNowPlayingListener = onNowPlayingListener;
    }

    public void setItem(List<ResultNowPlaying.Result> resultNowPlaying){
        this.mNowPlayingList = resultNowPlaying;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolderNowPlaying onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item,parent,false);
        return new ViewHolderNowPlaying(view , mOnNowPlayingListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderNowPlaying holder, int position) {
        ResultNowPlaying.Result resultNowPlaying = mNowPlayingList.get(position);
        holder.title.setText(resultNowPlaying.getTitle());
        holder.date.setText(resultNowPlaying.getRelease_date());
        holder.rate.setText(resultNowPlaying.getVote_average());
        Glide.with(mContext).asBitmap()
                .load("https://image.tmdb.org/t/p/w500" + resultNowPlaying.getPoster_path())
                .into(holder.movie_img);

    }

    @Override
    public int getItemCount() {

        return mNowPlayingList.size();
    }

    public static class ViewHolderNowPlaying extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title,date,rate;
        ImageView movie_img;
        OnNowPlayingListener mOnNowPlayingListener;
        public ViewHolderNowPlaying(@NonNull @NotNull View itemView , OnNowPlayingListener onNowPlayingListener) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title_text);
            date = itemView.findViewById(R.id.release_date_text);
            rate = itemView.findViewById(R.id.movie_rate_text);
            movie_img = itemView.findViewById(R.id.movie_image);
            this.mOnNowPlayingListener = onNowPlayingListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            mOnNowPlayingListener.OnPlayingClick(getPosition());
        }
    }

    public interface OnNowPlayingListener{
        void OnPlayingClick(int position);
    }
}
