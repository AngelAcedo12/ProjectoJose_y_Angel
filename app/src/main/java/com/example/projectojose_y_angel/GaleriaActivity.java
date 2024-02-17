package com.example.projectojose_y_angel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.projectojose_y_angel.models.Image;
import com.example.projectojose_y_angel.recycleAdapter.MyImageRecycleViewAdapter;
import com.example.projectojose_y_angel.repositorys.RepositoryImageInSmartphone;
import com.example.projectojose_y_angel.utils.loaderImageInBackGround;

import java.util.List;

public class GaleriaActivity extends AppCompatActivity implements MyImageRecycleViewAdapter.ItemClickListener,loaderImageInBackGround.TaskCompleted {

    RecyclerView galleryRecycleView;
    MyImageRecycleViewAdapter myImageRecycleViewAdapter;
    private int numeroColumnas=3;
    boolean isLoader=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);


        galleryRecycleView = findViewById(R.id.galleryRecycleView);

    }

    @Override
    public void onTaskCompleded(boolean res) {
        isLoader=true;
        List<Image> imagenes=RepositoryImageInSmartphone.getInstance().getImages();
        myImageRecycleViewAdapter = new MyImageRecycleViewAdapter(imagenes,this);
        myImageRecycleViewAdapter.setCLickListener(this);
        galleryRecycleView.setLayoutManager(new GridLayoutManager(this,numeroColumnas));
        galleryRecycleView.setAdapter(myImageRecycleViewAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isLoader){
            loaderImageInBackGround loader = new loaderImageInBackGround(this,GaleriaActivity.this);
            loader.execute();
        }
    }

    @Override
    public void onItemClick(View activista, int position) {
        Toast.makeText(this,"pulsado la pos" + position,Toast.LENGTH_SHORT).show();
    }
}