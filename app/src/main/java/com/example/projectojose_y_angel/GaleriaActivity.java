package com.example.projectojose_y_angel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.example.projectojose_y_angel.adapters.GalletyAdapter;
import com.example.projectojose_y_angel.repositorys.RepositoryImageInSmartphone;
import com.example.projectojose_y_angel.utils.loaderImageInBackGround;

public class GaleriaActivity extends AppCompatActivity implements loaderImageInBackGround.TaskCompleted {

    GridView gallery;
    boolean isLoader=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);


        gallery = (GridView) findViewById(R.id.galleryView);



        gallery.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public void onTaskCompleded(boolean res) {
        isLoader=true;
        GalletyAdapter galletyAdapter = new GalletyAdapter(this);
        galletyAdapter.setImagensArray(RepositoryImageInSmartphone.getInstance().getImages());
        gallery.setAdapter(galletyAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isLoader){
            loaderImageInBackGround loader = new loaderImageInBackGround(this,GaleriaActivity.this);
            loader.execute();
        }


    }
}