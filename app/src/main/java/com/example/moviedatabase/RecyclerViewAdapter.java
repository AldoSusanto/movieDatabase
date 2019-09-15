package com.example.moviedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder> {

    private ListFragment.listListener mListener;
    private List<Movie> mData;

    public RecyclerViewAdapter(ListFragment.listListener mListener, List<Movie> mData) {
        this.mListener = mListener;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_movie, parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.movieTitle.setText(mData.get(position).getTitle());
        holder.movieDesc.setText(mData.get(position).getOverview());
        //holder.movieImg.setImageResource(R.drawable.sample_image);

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + mData.get(position).getPosterUrl()).into(holder.movieImg);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView movieTitle;
        TextView movieDesc;
        ImageView movieImg;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            movieTitle = (TextView) itemView.findViewById(R.id.movie_title_id);
            movieDesc = (TextView) itemView.findViewById(R.id.movie_desc);
            movieImg = (ImageView) itemView.findViewById(R.id.movie_image);
        }
    }
}
