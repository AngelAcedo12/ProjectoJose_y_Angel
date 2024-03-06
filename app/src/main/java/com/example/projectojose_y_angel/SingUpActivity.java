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
import com.example.projectojose_y_angel.services.User.InsertarUsuario;

public class SingUpActivity extends AppCompatActivity implements InsertarUsuario.TaskCompleteRegisterUser {

    EditText editEmail;
    EditText editUsername;
    EditText editPassword;
    Button buttonLogIN;
    Button buttonRegister;
    User user =null;
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


            String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$";



            String username = editUsername.getText().toString();
            String email = editEmail.getText().toString();
            String password = editPassword.getText().toString();



             user = new User(username,email,password);
            InsertarUsuario insertarUsuario = new InsertarUsuario(this,this);
            insertarUsuario.execute(user);
        });

        buttonLogIN.setOnClickListener(e -> {
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
            finish();
        });
    }


    @Override
    public void TaskCompleteRegisterUser(boolean estate) {
        if (estate){

                Toast.makeText(this, "Registrado", Toast.LENGTH_SHORT).show();
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

        }else{
            Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
        }
    }
    public  boolean validarPassword(String password) {
        // Expresión regular que verifica todos los criterios
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$";

        // Verificar si la contraseña coincide con la expresión regular
        return password.matches(regex);
    }
}