package com.example.it19004778mad2019a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.it19004778mad2019a.DATABASE.DBHandler;

import java.util.ArrayList;

public class MovieList extends AppCompatActivity {
    RecyclerView recyclerView;
    DBHandler dbHandler;
    MovieAdapter movieAdapter;
    ArrayList <String> id,mName,mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        dbHandler = new DBHandler(getApplicationContext());
         recyclerView = findViewById(R.id.res1);
         id= new ArrayList<>();
         mName = new ArrayList<>();
         mYear = new ArrayList<>();
         storeDataInArray();
         movieAdapter = new MovieAdapter(MovieList.this,this,id,mName,mYear);
         recyclerView.setAdapter(movieAdapter);
         recyclerView.setLayoutManager(new LinearLayoutManager((MovieList.this)));
    }
    void storeDataInArray(){
        Cursor cursor = dbHandler.viewMovies();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data!", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                mName.add(cursor.getString(1));
                mYear.add(cursor.getString(2));
            }
        }
    }
}