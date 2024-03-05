package com.example.projectojose_y_angel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.projectojose_y_angel.models.Image;

public class FotoView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_view);

        Intent intent = getIntent();
        String volumeName = intent.getStringExtra("");
      //  Uri uri = intent.getStringExtra();
        int id = intent.getIntExtra("id",0);

        Image image = new Image();




    }
}