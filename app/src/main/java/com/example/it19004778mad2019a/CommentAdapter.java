package com.example.it19004778mad2019a;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.it19004778mad2019a.DATABASE.DBHandler;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private Context context;
    private ArrayList id,mName,comment;
    int position;
    Activity activity;

    CommentAdapter(Activity activity, Context context, ArrayList id, ArrayList mName, ArrayList comment){
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.mName = mName;
        this.comment = comment;
    }

    @NonNull
    @Override
    public CommentAdapter.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.comment_row, parent,false);
        return new CommentAdapter.CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentViewHolder holder, int position) {
        this.position = position;
        holder.tvComment.setText(String.valueOf(comment.get(position)));
    }

    @Override
    public int getItemCount() {return id.size();}

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView tvComment;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvComment = itemView.findViewById(R.id.tvComment);
            linearLayout = itemView.findViewById(R.id.comment_layout);
        }
    }
}
