package com.example.projectojose_y_angel;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

public class PermisionActivity extends AppCompatActivity {

    Button btnPermision;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permision);
        btnPermision=findViewById(R.id.buttonRequestPermision);
        if(checkPermission()){
            Intent intent = new Intent(this,GaleriaActivity.class);
            startActivity(intent);
            finish();
        }

        btnPermision.setOnClickListener(e -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermission();
            }else{
                requestPermissionOld();
            }
        });



    }
    public boolean checkPermission(){
        int resulltExternalStorage= ContextCompat.checkSelfPermission(PermisionActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE);
        int resultMusicStorage = ContextCompat.checkSelfPermission(PermisionActivity.this,Manifest.permission.READ_MEDIA_IMAGES);
        return (resultMusicStorage == PackageManager.PERMISSION_GRANTED
                || resulltExternalStorage == PackageManager.PERMISSION_GRANTED)
                ;
    }
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public void requestPermission(){
        String readMedia = Manifest.permission.READ_MEDIA_IMAGES;
        String postNotification  = Manifest.permission.READ_EXTERNAL_STORAGE;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(PermisionActivity.this, readMedia)) {
                ActivityCompat.shouldShowRequestPermissionRationale(PermisionActivity.this, postNotification);
            }
            ActivityCompat.requestPermissions(this,new String[]{readMedia,postNotification},0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(this,GaleriaActivity.class);
                startActivity(
                        intent
                );
                finish();
            } else {
                finish();
            }
        }
    }

    public void requestPermissionOld(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(PermisionActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)){

        }
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
    }
}