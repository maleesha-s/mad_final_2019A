package com.example.it19004778mad2019a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.it19004778mad2019a.DATABASE.DBHandler;

public class AddMovie extends AppCompatActivity {
    EditText mName,mYear;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        mName = findViewById(R.id.etMovieName);
        mYear = findViewById(R.id.etMovieYear);
        btnAdd = findViewById(R.id.btnAddAM);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                boolean res = dbHandler.addMovie(mName.getText().toString().trim(),Integer.parseInt(mYear.getText().toString().trim()));

                if(res){
                    Toast.makeText(AddMovie.this, "Successful!", Toast.LENGTH_SHORT).show();
                    mName.setText(null);
                    mYear.setText(null);
                }else{
                    Toast.makeText(AddMovie.this, "Unsuccessful!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}