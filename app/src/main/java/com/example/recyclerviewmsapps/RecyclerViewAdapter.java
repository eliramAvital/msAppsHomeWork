package com.example.recyclerviewmsapps;

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

import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myView> {

    private Context mContext;
    private List<Movie> mData;
    RequestOptions options;

    public RecyclerViewAdapter(Context mContext, List<Movie> mData) {
        this.mContext = mContext;
        this.mData = mData;
        Collections.sort(this.mData,Collections.<Movie>reverseOrder());
        options = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background);
    }

    @NonNull
    @Override
    public myView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.movie_item, parent , false);


        return new myView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myView holder, int position) {

        holder.tvTitle.setText(mData.get(position).getTitle());
        holder.tvRating.setText(mData.get(position).getRating());
        holder.tvRelease.setText(mData.get(position).getReleaseYear());
        holder.tvGenre.setText(mData.get(position).getGenre());

        Glide.with(mContext).load(mData.get(position).getImage()).apply(options).into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class myView extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvRating;
        TextView tvRelease;
        TextView tvGenre;
        ImageView ivImage;
        public myView(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvRelease = itemView.findViewById(R.id.tvRelease);
            tvGenre = itemView.findViewById(R.id.tvGenre);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
