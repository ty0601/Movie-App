package com.example.pd2_movie_app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.pd2_movie_app.R;
import com.example.pd2_movie_app.model.ResultPopular;
import com.example.pd2_movie_app.model.ResultTopRate;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterTopRate extends RecyclerView.Adapter<AdapterTopRate.ViewHolderTopRate> {
    private static final String TAG = "AdapterTopRate";

    private List<ResultTopRate.Result> resultTopRateList = new ArrayList<>();
    private Context context;
    private OnTopRateListener onTopRateListener;
    private String overview;

    public AdapterTopRate(Context context ,OnTopRateListener onTopRateListener) {
        this.context = context;
        this.onTopRateListener = onTopRateListener;
    }


    public void setItem (List<ResultTopRate.Result> resultList){
        Log.d(TAG, "setItem: called");
        this.resultTopRateList = resultList;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolderTopRate onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: top rate");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolderTopRate(view ,onTopRateListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderTopRate holder, int position) {
        Log.d(TAG, "onBindViewHolder: top rate");
        ResultTopRate.Result resultTopRate = resultTopRateList.get(position);
        holder.title.setText(resultTopRate.getTitle());
        holder.date.setText(resultTopRate.getRelease_date());
        holder.rate.setText(resultTopRate.getVote_average());
        Glide.with(context).asBitmap()
                .load("https://image.tmdb.org/t/p/w500" + resultTopRate.getPoster_path())
                .into(holder.movie_img);
        overview = resultTopRate.getOverview();
    }

    @Override
    public int getItemCount() {
        return resultTopRateList.size();
    }

    public static class ViewHolderTopRate extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final String TAG = "ViewHolderTopRate";
        TextView title, date, rate;
        ImageView movie_img;
        OnTopRateListener onTopRateListener;
        public ViewHolderTopRate(@NonNull @NotNull View itemView , OnTopRateListener onTopRateListener) {
            super(itemView);
            Log.d(TAG, "ViewHolderTopRate: top rate");
            title = itemView.findViewById(R.id.movie_title_text);
            date = itemView.findViewById(R.id.release_date_text);
            rate = itemView.findViewById(R.id.movie_rate_text);
            movie_img = itemView.findViewById(R.id.movie_image);
            this.onTopRateListener = onTopRateListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onTopRateListener.onTopClick(getPosition());
        }
    }
    public String getOverview (){
        return overview;
    }

    public interface OnTopRateListener{
        void onTopClick(int position);
    }
}
