package com.example.it19004778mad2019a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolde> {
    private Context context;
    private ArrayList mName,mYear, mId;
    int position;
    Activity activity;

    MovieAdapter(Activity activity, Context context,ArrayList id,  ArrayList movieName, ArrayList movieYear){
        this.activity = activity;
        this.context = context;
        this.mId = id;
        this.mName = movieName;
        this.mYear = movieYear;
    }
    @NonNull
    @Override
    public MovieAdapter.MovieViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_row, parent, false);
        return new MovieAdapter.MovieViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolde holder, int position) {
        this.position = position;
        holder.tvMovieName.setText(String.valueOf(mName.get(position)));
        holder.tvMovieYear.setText(String.valueOf(mYear.get(position)));
        String nam = String.valueOf(mName.get(position));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,MovieOverview.class);
                i.putExtra("Name", nam);
                activity.startActivityForResult(i,1);
            }
        });
    }

    @Override
    public int getItemCount() {return mId.size();   }

    public class MovieViewHolde extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView tvMovieName, tvMovieYear;
        public MovieViewHolde(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.movie_details);
            tvMovieName = itemView.findViewById(R.id.tvMovie_name);
            tvMovieYear = itemView.findViewById(R.id.tvMovie_year);
        }
    }
}
