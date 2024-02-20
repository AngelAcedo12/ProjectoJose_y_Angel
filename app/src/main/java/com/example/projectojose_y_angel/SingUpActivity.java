package com.example.projectojose_y_angel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectojose_y_angel.models.User;
import com.example.projectojose_y_angel.repositorys.RepositoryUser;
import com.example.projectojose_y_angel.repositorys.RepositoryUserImpLocal;

public class SingUpActivity extends AppCompatActivity {

    EditText editEmail;
    EditText editUsername;
    EditText editPassword;
    Button buttonLogIN;
    Button buttonRegister;
    RepositoryUser repositoryUser = new RepositoryUserImpLocal();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        buttonRegister=findViewById(R.id.buttonSinUpConfirmer);
        buttonLogIN=findViewById(R.id.buttonLogInRedirect);
        editEmail=findViewById(R.id.editTextTextEmailAddressSingUp);
        editPassword=findViewById(R.id.editTextTextPasswordSingUp);
        editUsername=findViewById(R.id.editTextTextUsernameSingUp);
        repositoryUser=new RepositoryUserImpLocal();


        buttonRegister.setOnClickListener(e -> {
            String username = editUsername.getText().toString();
            String email = editEmail.getText().toString();
            String password = editPassword.getText().toString();
            User user = new User(username,email,password);
            if(repositoryUser.create(user)){
                Toast.makeText(this, "Registrado", Toast.LENGTH_SHORT).show();
                SharedPreferences pref = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putString("email",user.getEmail());
                editor.putString("username",user.getUser());
                editor.putString("password",user.getPassword());
                editor.apply();
                Intent intent = new Intent(this,PermisionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogIN.setOnClickListener(e -> {
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
            finish();
        });


    }
}