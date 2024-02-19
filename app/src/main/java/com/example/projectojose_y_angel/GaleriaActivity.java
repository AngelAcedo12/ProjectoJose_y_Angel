package com.example.projectojose_y_angel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.example.projectojose_y_angel.models.Image;
import com.example.projectojose_y_angel.recycleAdapter.MyImageRecycleViewAdapter;
import com.example.projectojose_y_angel.repositorys.RepositoryImageInSmartphone;
import com.example.projectojose_y_angel.utils.loaderImageInBackGround;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class GaleriaActivity extends AppCompatActivity implements MyImageRecycleViewAdapter.ItemClickListener,loaderImageInBackGround.TaskCompleted {

    RecyclerView galleryRecycleView;
    MyImageRecycleViewAdapter myImageRecycleViewAdapter;
    boolean isLoader=false;

    //buttons menu
    private FloatingActionButton mainMenuFloatingButton;
    private FloatingActionButton galleryButton;
    private FloatingActionButton cloudButton;
    private View transparentBackgroundView;
    private boolean isExpanded=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        galleryRecycleView = findViewById(R.id.galleryRecycleView);
        asignarElementosMenuFlotante();
        asignarComportamientoMenuFlotante();

    }

    public void asignarElementosMenuFlotante(){
        this.mainMenuFloatingButton=findViewById(R.id.mainFloatingButton);
        this.galleryButton=findViewById(R.id.galleryButton);
        this.cloudButton=findViewById(R.id.cloudButton);
        this.transparentBackgroundView = findViewById(R.id.transparentBg);
    }

    public void asignarComportamientoMenuFlotante(){
        mainMenuFloatingButton.setOnClickListener(v -> {
            if(isExpanded){
                contraerMenu();
            }else{
                expandirMenu();
            }
        });

    }
    private void contraerMenu(){
        isExpanded=!isExpanded;
        Animation to_bottom_anim_bg=AnimationUtils.loadAnimation(this,R.anim.to_bottom_anim);
        Animation rotate_anti_clock_wise=AnimationUtils.loadAnimation(this,R.anim.rotate_anti_clock_wise);
        Animation to_bottom_fab=AnimationUtils.loadAnimation(this,R.anim.to_bottom_fab);


        transparentBackgroundView.startAnimation(to_bottom_anim_bg);

        mainMenuFloatingButton.startAnimation(rotate_anti_clock_wise);

        galleryButton.startAnimation(to_bottom_fab);
        cloudButton.startAnimation(to_bottom_fab);



    }
    private void expandirMenu(){
        isExpanded=!isExpanded;
        Animation from_bottom_anim_bg=AnimationUtils.loadAnimation(this,R.anim.from_bottom_anim);
        Animation rotate_clock_wise=AnimationUtils.loadAnimation(this,R.anim.rotate_clock_wise);
        Animation from_bottom_fab=AnimationUtils.loadAnimation(this,R.anim.from_bottom_fab);


        transparentBackgroundView.startAnimation(from_bottom_anim_bg);

        mainMenuFloatingButton.startAnimation(rotate_clock_wise);

        galleryButton.startAnimation(from_bottom_fab);
        cloudButton.startAnimation(from_bottom_fab);



    }


    @Override
    public void onTaskCompleded(boolean res) {
        isLoader=true;
        HashMap<LocalDate,List<Image>> imagenes=RepositoryImageInSmartphone.getInstance().getImages();
        myImageRecycleViewAdapter = new MyImageRecycleViewAdapter(imagenes,this);
        myImageRecycleViewAdapter.setCLickListener(this);
        galleryRecycleView.setLayoutManager(new LinearLayoutManager(this));
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // Es decir, si hago click y está expandido
        if (ev.getAction() == MotionEvent.ACTION_DOWN && isExpanded) {
            Rect outrect = new Rect();
            mainMenuFloatingButton.getGlobalVisibleRect(outrect);
            //Y pulso en cualquier lugar que no sea el mainMenu
            if( !outrect.contains(Math.round(ev.getRawX()),Math.round(ev.getRawY())) ){
                contraerMenu();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}