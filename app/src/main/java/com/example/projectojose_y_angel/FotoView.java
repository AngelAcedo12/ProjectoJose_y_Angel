package com.example.projectojose_y_angel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projectojose_y_angel.models.Image;
import com.example.projectojose_y_angel.repositorys.RepositoryImageInSmartphone;
import com.squareup.picasso.Picasso;

public class FotoView extends AppCompatActivity {
    ImageView imagenFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_view);
        imagenFoto=findViewById(R.id.foto);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        String tye = intent.getStringExtra("type");
        Image image = new Image();
        Image image1 = RepositoryImageInSmartphone.getInstance().getImages().get(id);
        Picasso.with(this).load(image1.getUri()).into(imagenFoto);


    }
}