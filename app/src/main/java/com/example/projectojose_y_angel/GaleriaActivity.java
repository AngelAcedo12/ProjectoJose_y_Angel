package com.example.projectojose_y_angel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.projectojose_y_angel.models.Image;
import com.example.projectojose_y_angel.recycleAdapter.AdapterListImageOfTheDate;
import com.example.projectojose_y_angel.repositorys.RepositoryImageInSmartphone;
import com.example.projectojose_y_angel.utils.LoaderImageInBackGround;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class GaleriaActivity extends AppCompatActivity implements AdapterListImageOfTheDate.ItemClickListener
        , LoaderImageInBackGround.TaskCompleted {

    RecyclerView galleryRecycleView;
    AdapterListImageOfTheDate myImageRecycleViewAdapter;
    boolean isLoader=false;
    boolean floatingUpIsVisile=false;
    //buttons menu
    private FloatingActionButton floatingUp;
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

        GaleriaActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    Log.i("imgActives",String.valueOf(myImageRecycleViewAdapter.getImgActived()));
                    if (!floatingUpIsVisile) {
                        if (myImageRecycleViewAdapter.getImgActived() > 0) {
                            floatingUpIsVisile = true;
                            floatingUp.setVisibility(View.VISIBLE);
                        } else {
                            floatingUp.setVisibility(View.INVISIBLE);
                            floatingUpIsVisile = false;
                        }
                    }
                }catch (Exception err){
                    err.printStackTrace();
                }
                new Handler().postDelayed(this,10);
            }
        });

    }

    public void asignarElementosMenuFlotante(){
        this.mainMenuFloatingButton=findViewById(R.id.mainFloatingButton);
        this.galleryButton=findViewById(R.id.galleryButton);
        this.cloudButton=findViewById(R.id.cloudButton);
        this.transparentBackgroundView = findViewById(R.id.transparentBg);
        this.floatingUp=findViewById(R.id.floatingUp);
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
    private void mostrarFloatingup(){
        Animation fadeIn=null;
        if (floatingUpIsVisile){
            fadeIn = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        }else {

        }

        floatingUp.startAnimation(fadeIn);
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
        List<Image> imagenes=RepositoryImageInSmartphone.getInstance().getImages();
        myImageRecycleViewAdapter = new AdapterListImageOfTheDate(imagenes,this);
        myImageRecycleViewAdapter.setCLickListener( this);
        galleryRecycleView.setLayoutManager(new GridLayoutManager(this,3));
        galleryRecycleView.setAdapter(myImageRecycleViewAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isLoader){
            LoaderImageInBackGround loader = new LoaderImageInBackGround(this,GaleriaActivity.this);
            loader.execute();
        }
    }

    @Override
    public void onItemClick(View activista, int position) {
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