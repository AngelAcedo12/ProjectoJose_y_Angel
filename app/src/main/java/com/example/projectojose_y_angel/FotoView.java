package com.example.projectojose_y_angel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projectojose_y_angel.models.Image;
import com.example.projectojose_y_angel.recycleAdapter.AdapterImageForClud;
import com.example.projectojose_y_angel.repositorys.RepositoryImageInSmartphone;
import com.example.projectojose_y_angel.utils.Mappeds.MapByteArrayToBitmap;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class FotoView extends AppCompatActivity {
    ImageView imagenFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_view);
        imagenFoto=findViewById(R.id.foto);
        Intent intent = getIntent();

        String type = intent.getStringExtra("type");

        if(type.equals("local")){
            Image image = new Image();
            int id = intent.getIntExtra("id",0);
            Image image1 = RepositoryImageInSmartphone.getInstance().getImages().get(id);
            Picasso.with(this).load(image1.getUri()).into(imagenFoto);
        }else if(type.equals("cloud")){
            int post= intent.getIntExtra("pos",0);
            Bitmap bitmap=   AdapterImageForClud.getImageInPosition(post).getBitmap();
            imagenFoto.setImageBitmap(bitmap);
        }


    }
}