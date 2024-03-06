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
import com.example.projectojose_y_angel.utils.GetUserByName;

import java.util.Optional;

public class MainActivity extends AppCompatActivity  implements GetUserByName.TaskCompleteLogin {

    private Button buttonLog;
    private Button buttonSingUp;
    User user = new User();
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

        });
        buttonSingUp.setOnClickListener(e -> {
            Intent intent = new Intent(this, SingUpActivity.class);
            startActivity(intent);

        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
        String username = sharedPreferences.getString("username",null);
        String password =sharedPreferences.getString("password",null);
        String email =sharedPreferences.getString("email",null);

        user.setName(username);
        user.setPassword(username);
        user.setEmail(username);

        GetUserByName getUserByName = new GetUserByName(this,this);
        getUserByName.execute(user);
    }

    @Override
    public void onTaskCompleteLogin(Optional<User> optionalUser) {

        if (optionalUser.isPresent()){
            if (user.getPassword().equals(optionalUser.get().getPassword()) && user.getUser().equals(optionalUser.get().getUser())){
                Intent intent = new Intent(this,PermisionActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}