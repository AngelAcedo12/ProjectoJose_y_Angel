package com.example.projectojose_y_angel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {

    private Button buttonLog;
    private Button buttonSingUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLog=findViewById(R.id.buttonLogIn);
        buttonSingUp=findViewById(R.id.buttonSingUp);




    }



}