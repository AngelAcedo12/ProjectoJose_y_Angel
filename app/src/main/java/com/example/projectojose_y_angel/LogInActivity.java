package com.example.projectojose_y_angel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectojose_y_angel.models.User;
import com.example.projectojose_y_angel.repositorys.RepositoryUser;
import com.example.projectojose_y_angel.repositorys.RepositoryUserImpLocal;

import java.util.Optional;

public class LogInActivity extends AppCompatActivity {

    EditText editEmail;
    EditText editPassword;
    Button buttonSingUp;
    Button buttonLogIn;
    RepositoryUser repositoryUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editEmail =findViewById(R.id.editTextTextEmailAddress);
        editPassword=findViewById(R.id.editTextTextPassword);
        buttonSingUp=findViewById(R.id.buttonSingUp2);
        buttonLogIn=findViewById(R.id.buttonLogIn2);

        repositoryUser=new RepositoryUserImpLocal();


        buttonSingUp.setOnClickListener(e -> {
            Intent intent = new Intent(this,SingUpActivity.class);
            startActivity(intent);
            finish();
        });
        buttonLogIn.setOnClickListener(e -> {
            Optional<User> optionalUser = repositoryUser.findByEmail(editEmail.getText().toString());
            if (optionalUser.isPresent()){
                SharedPreferences pref = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("email",optionalUser.get().getUser());
                editor.commit();
                Intent intent = new Intent(this,PermisionActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "Este usuario no existe o la contraseña es incorrecta", Toast.LENGTH_SHORT).show();
            }

        });
    }

}