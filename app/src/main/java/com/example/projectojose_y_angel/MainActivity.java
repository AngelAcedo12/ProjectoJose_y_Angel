package com.example.projectojose_y_angel;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.projectojose_y_angel.models.User;
import com.example.projectojose_y_angel.repositorys.RepositoryUser;
import com.example.projectojose_y_angel.repositorys.RepositoryUserImpLocal;

import java.util.Optional;

public class MainActivity extends AppCompatActivity  {

    private Button buttonLog;
    private Button buttonSingUp;
    private  RepositoryUser repositoryUser = new RepositoryUserImpLocal();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLog=findViewById(R.id.buttonLogIn);
        buttonSingUp=findViewById(R.id.buttonSingUp);

        buttonLog.setOnClickListener(e -> {
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
            finish();
        });
        buttonSingUp.setOnClickListener(e -> {
            Intent intent = new Intent(this, SingUpActivity.class);
            startActivity(intent);
            finish();
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
        String email = sharedPreferences.getString("email",null);
        if (email!=null){

            Optional<User> optionalUser  = repositoryUser.findByEmail(email);
            if (optionalUser.isPresent()){
                Intent intent = new Intent(this, PermisionActivity.class);
                startActivity(intent);
                finish();
            }
        }else {

            Toast.makeText(this, "Soy nulo", Toast.LENGTH_SHORT).show();
        }



    }
}