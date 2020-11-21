package com.example.it19004778mad2019a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.it19004778mad2019a.DATABASE.DBHandler;

public class MainActivity extends AppCompatActivity {
    EditText userName,Password;
    Button btnLogin,btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.etUserNameM);
        Password = findViewById(R.id.etPasswordM);
        btnLogin = findViewById(R.id.btnLoginM);
        btnRegister = findViewById(R.id.btnRegisterM);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userName.getText().toString().trim().equals("admin")){
                    Intent i = new Intent(getApplicationContext(),AddMovie.class);
                    startActivity(i);
                }else{
                    DBHandler dbHandler = new DBHandler(getApplicationContext());
                    boolean status = dbHandler.LoginUser(userName.getText().toString().trim());

                    if(status){
                        Intent i = new Intent(getApplicationContext(),MovieList.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(MainActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                long res = dbHandler.registerUser(userName.getText().toString().trim(),Password.getText().toString().trim(),"user");

                if(res > 0){
                    Toast.makeText(MainActivity.this, "Successfully inserted!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Unsuccessfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}