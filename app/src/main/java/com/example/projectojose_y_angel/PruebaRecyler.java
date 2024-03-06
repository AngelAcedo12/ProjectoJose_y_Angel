package com.example.projectojose_y_angel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.projectojose_y_angel.models.Image;
import com.example.projectojose_y_angel.models.User;
import com.example.projectojose_y_angel.recycleAdapter.AdapterImageForClud;
import com.example.projectojose_y_angel.utils.LoadAlllImageInCloud;

import java.util.List;

public class PruebaRecyler extends AppCompatActivity implements   LoadAlllImageInCloud.ToaskImageComplete,AdapterImageForClud.ItemClickListener {

    RecyclerView recyclerView;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_recyler);
        recyclerView=findViewById(R.id.recyclerImgCloud);

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




    }
    @Override
    public void ToaskImageLoadComplete(List<Image> list) {
        Toast.makeText(this, "Carga completa", Toast.LENGTH_SHORT).show();
        AdapterImageForClud adapterImageForClud = new AdapterImageForClud(this,list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(adapterImageForClud);
        adapterImageForClud.setCLickListener(this);
        adapterImageForClud.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View activista, int position) {

    }
}