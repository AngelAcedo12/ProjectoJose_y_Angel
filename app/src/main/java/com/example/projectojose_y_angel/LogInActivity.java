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
import com.example.projectojose_y_angel.utils.GetUserByName;

import java.util.Optional;

public class LogInActivity extends AppCompatActivity implements GetUserByName.TaskCompleteLogin {

    EditText editUsername;
    EditText editPassword;
    Button buttonSingUp;
    Button buttonLogIn;
    User user = new User();
    RepositoryUser repositoryUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editUsername =findViewById(R.id.editTextTextEmailAddress);
        editPassword=findViewById(R.id.editTextTextPassword);
        buttonLogIn=findViewById(R.id.btnLogIn2);
        buttonSingUp=findViewById(R.id.buttonSingUp2);
        repositoryUser=new RepositoryUserImpLocal();

        buttonSingUp.setOnClickListener(e -> {
            Intent intent = new Intent(this,SingUpActivity.class);
            startActivity(intent);
            finish();
        });
        buttonLogIn.setOnClickListener(e -> {


            user.setName(editUsername.getText().toString());
            user.setPassword(editPassword.getText().toString());
            GetUserByName getUserByName = new GetUserByName(this,this);
            getUserByName.execute(user);

        });
    }

    @Override
    public void onTaskCompleteLogin(Optional<User> optionalUser) {
     if (!optionalUser.isPresent()){
         Toast.makeText(this, "Este usuario no existe", Toast.LENGTH_SHORT).show();
         editPassword.setText("");
         editUsername.setText("");
     }else{
         User userGet = optionalUser.get();
            if (userGet.getPassword().equals(user.getPassword())){
                SharedPreferences pref = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("email",user.getEmail());
                editor.putString("username",user.getUser());
                editor.putString("password",user.getPassword());
                if(editor.commit()){
                    Intent intent = new Intent(this,PermisionActivity.class);
                    startActivity(intent);
                    finish();
                };

            }
     }



    }
}