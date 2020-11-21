package com.example.it19004778mad2019a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.it19004778mad2019a.DATABASE.DBHandler;

import java.util.ArrayList;

public class MovieOverview extends AppCompatActivity {
    SeekBar seekBar;
    TextView rate;
    EditText comment;
    Button btnSubmit;
    int progress1 = 0;
    String name;
    RecyclerView recyclerView;
    DBHandler dbHandler;
    CommentAdapter commentAdapter;
    ArrayList<String> id,mName,mComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_overview);

        Intent i = getIntent();
        name = i.getStringExtra("Name");

        dbHandler = new DBHandler(getApplicationContext());
        recyclerView = findViewById(R.id.res2);
        id = new ArrayList<>();
        mName = new ArrayList<>();
        mComment = new ArrayList<>();

        rate = findViewById(R.id.tvRate);
        comment = findViewById(R.id.etCommentMO);
        seekBar = findViewById(R.id.seekBarMO);
        btnSubmit = findViewById(R.id.btnSubmitMO);

        try{
            double res = calculateRatings();
            rate.setText(String.valueOf(res));
        }catch (Exception e){
            Toast.makeText(this, "rate Calculate error", Toast.LENGTH_SHORT).show();
        }

        try {
            StoreDataInArray();
        }catch (Exception e){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        commentAdapter = new CommentAdapter(MovieOverview.this,this,id,mName,mComment);
        recyclerView.setAdapter(commentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MovieOverview.this));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress1 = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                long res = dbHandler.insertComments(name,progress1,comment.getText().toString().trim());
                if(res > 0){
                    Toast.makeText(MovieOverview.this, "Successful!", Toast.LENGTH_SHORT).show();
                    comment.setText(null);
                }else{
                    Toast.makeText(MovieOverview.this, "Unsuccessful!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public Double calculateRatings(){
        DBHandler dbHandler = new DBHandler(getApplicationContext());
        Cursor cursor = dbHandler.viewComments();
        int rate = 0;
        int count = 0;
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                if(cursor.getString(1).equals(name)){
                    rate = rate + Integer.parseInt(cursor.getString(2));
                    count = count + 1;

                }
            }

        }
        double res = rate / count;
        return res;
    }
    void StoreDataInArray(){
        Cursor cursor = dbHandler.viewComments(name);
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data!", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                mName.add(cursor.getString(1));
                mComment.add(cursor.getString(3));
            }
        }
    }
}