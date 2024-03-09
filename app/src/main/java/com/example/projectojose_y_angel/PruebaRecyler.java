package com.example.projectojose_y_angel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.projectojose_y_angel.models.Image;
import com.example.projectojose_y_angel.models.User;
import com.example.projectojose_y_angel.recycleAdapter.AdapterImageForClud;
import com.example.projectojose_y_angel.utils.DeleteImg;
import com.example.projectojose_y_angel.utils.LoadAlllImageInCloud;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PruebaRecyler extends AppCompatActivity implements DeleteImg.TaskDeleteComplete,   LoadAlllImageInCloud.ToaskImageComplete,AdapterImageForClud.ItemClickListener {

    RecyclerView recyclerView;
    User user;
    AdapterImageForClud adapterImageForClud;

    //buttons menu
    private FloatingActionButton mainMenuFloatingButton;
    private FloatingActionButton galleryButton;
    private  FloatingActionButton exitButton;
    private boolean isExpanded = false;
    private FloatingActionButton deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_recyler);
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
        asignarComportamientoSalir();
        asignarComportamientoBorrar();
    }

    public void asignarComportamientoBorrar() {
        deleteButton.setOnClickListener(e ->{
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Confirmacion de borrado");
            alert.setMessage("¿Seguro que quieres borrar estas imagenes?");
            alert.setPositiveButton("Aceptar",aceptDilog());
            alert.setNegativeButton("Cancelar",null);
            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        });
    }

    private DialogInterface.OnClickListener aceptDilog(){
        DeleteImg deleteImg = new DeleteImg(this,this);
        deleteImg.execute();
        return null;
    }


    public void asignarComportamientoSalir(){
        exitButton.setOnClickListener(e ->{
            if(!isExpanded){
                SharedPreferences pref = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }

        });
    }
    public void loadButtons(){
        mainMenuFloatingButton=findViewById(R.id.mainFloatingcloud);
        galleryButton=findViewById(R.id.galleryButtonCloud);
        exitButton=findViewById(R.id.exitButtonCloud);
        deleteButton=findViewById(R.id.delteImg);
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
        if (adapterImageForClud.getImgActived()<0){
            deleteButton.startAnimation(to_bottom_fab);
        }


    }

    private void expandirMenu() {
        isExpanded = !isExpanded;
        Animation rotate_clock_wise = AnimationUtils.loadAnimation(this, R.anim.rotate_clock_wise);
        Animation from_bottom_fab = AnimationUtils.loadAnimation(this, R.anim.from_bottom_fab);

        mainMenuFloatingButton.startAnimation(rotate_clock_wise);
        galleryButton.startAnimation(from_bottom_fab);
        exitButton.startAnimation(from_bottom_fab);
        if (adapterImageForClud.getImgActived()<0){
            deleteButton.startAnimation(from_bottom_fab);
        }


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
    public void onLongItemClick(View activista, int position) {

    }

    @Override
    public void onItemClick(View activista, int position) {

        if (adapterImageForClud.getImgActived()==0){
            Intent intent = new Intent(this,FotoView.class);
            intent.putExtra("type","cloud");
            intent.putExtra("pos",position);
            startActivity(intent);

        }

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

    @Override
    public void deleteTaskComplete(boolean b) {

    }



}