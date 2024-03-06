package com.example.projectojose_y_angel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.projectojose_y_angel.models.DTO.DtoUserImg;
import com.example.projectojose_y_angel.models.Image;
import com.example.projectojose_y_angel.models.User;
import com.example.projectojose_y_angel.recycleAdapter.AdapterImageForClud;
import com.example.projectojose_y_angel.utils.LoadAlllImageInCloud;
import com.example.projectojose_y_angel.utils.Mappeds.MapListImgToListDtoUserImg;
import com.example.projectojose_y_angel.utils.SaveImgForUser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.projectojose_y_angel.utils.Mappeds.MapBitmapToByteArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PruebaRecyler extends AppCompatActivity implements   LoadAlllImageInCloud.ToaskImageComplete,AdapterImageForClud.ItemClickListener {

    RecyclerView recyclerView;
    User user;
    AdapterImageForClud adapterImageForClud;

    //buttons menu
    private FloatingActionButton mainMenuFloatingButton;
    private FloatingActionButton galleryButton;
    private  FloatingActionButton exitButton;
    private boolean isExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_recyler);
        LOG.I
        recyclerView=findViewById(R.id.recyclerImgCloud);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        adapterImageForClud=new AdapterImageForClud(this,new ArrayList<>());
        recyclerView.setAdapter(adapterImageForClud);

        LoadAlllImageInCloud  loadAlllImageInCloud = new LoadAlllImageInCloud(this,this);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
        String username = sharedPreferences.getString("username",null);
        String password =sharedPreferences.getString("password",null);
        String email =sharedPreferences.getString("email",null);
        user  = new User();
        user.setName(username);
        user.setPassword(username);
        user.setEmail(username);
        loadAlllImageInCloud.execute(user);

        loadButtons();
        asignarComportamientoMenuFlotante();




    }

    public void loadButtons(){
        mainMenuFloatingButton=findViewById(R.id.mainFloatingcloud);
        galleryButton=findViewById(R.id.galleryButtonCloud);
        exitButton=findViewById(R.id.exitButtonCloud);
    }


    public void asignarComportamientoMenuFlotante() {
        mainMenuFloatingButton.setOnClickListener(v -> {
            if (isExpanded) {
                contraerMenu();
            } else {
                expandirMenu();
            }
        });

    }

    private void contraerMenu() {
        isExpanded = !isExpanded;
        Animation rotate_anti_clock_wise = AnimationUtils.loadAnimation(this, R.anim.rotate_anti_clock_wise);
        Animation to_bottom_fab = AnimationUtils.loadAnimation(this, R.anim.to_bottom_fab);


        mainMenuFloatingButton.startAnimation(rotate_anti_clock_wise);
        galleryButton.startAnimation(to_bottom_fab);
        exitButton.startAnimation(to_bottom_fab);

    }

    private void expandirMenu() {
        isExpanded = !isExpanded;
        Animation rotate_clock_wise = AnimationUtils.loadAnimation(this, R.anim.rotate_clock_wise);
        Animation from_bottom_fab = AnimationUtils.loadAnimation(this, R.anim.from_bottom_fab);

        mainMenuFloatingButton.startAnimation(rotate_clock_wise);
        galleryButton.startAnimation(from_bottom_fab);
        exitButton.startAnimation(from_bottom_fab);

        galleryButton.setOnClickListener(e->{
            Intent intent = new Intent(this,GaleriaActivity.class);
            startActivity(intent);
            finish();
        });

    }

    @Override
    public void ToaskImageLoadComplete(List<Image> list) {
        Toast.makeText(this, "Carga completa", Toast.LENGTH_SHORT).show();
        adapterImageForClud = new AdapterImageForClud(this,list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(adapterImageForClud);
        adapterImageForClud.setCLickListener(this);
        adapterImageForClud.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View activista, int position) {
        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,FotoView.class);
        intent.putExtra("type","cloud");
        intent.putExtra("pos",position);
        startActivity(intent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // Es decir, si hago click y está expandido
        if (ev.getAction() == MotionEvent.ACTION_DOWN && isExpanded) {
            Rect outrect = new Rect();
            mainMenuFloatingButton.getGlobalVisibleRect(outrect);
            //Y pulso en cualquier lugar que no sea el mainMenu
            if (!outrect.contains(Math.round(ev.getRawX()), Math.round(ev.getRawY()))) {
                contraerMenu();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}