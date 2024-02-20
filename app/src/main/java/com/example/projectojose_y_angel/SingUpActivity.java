package com.example.projectojose_y_angel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectojose_y_angel.repositorys.RepositoryUser;
import com.example.projectojose_y_angel.repositorys.RepositoryUserImpLocal;

public class SingUpActivity extends AppCompatActivity {

    EditText editEmail;
    EditText editUsername;
    EditText editPassword;
    Button buttonLogIN;
    Button buttonRegister;
    RepositoryUser repositoryUser;
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



    }
}