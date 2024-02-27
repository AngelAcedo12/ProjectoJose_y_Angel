package com.example.projectojose_y_angel.services.User;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.projectojose_y_angel.models.User;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class InsertarUsuario extends AsyncTask<User, Void, Boolean> {

    private Context context;
    private ProgressDialog progressDialog;

    private TaskCompleteRegisterUser taskCompleteRegisterUser;

    public InsertarUsuario(Context context,  TaskCompleteRegisterUser taskCompleteRegisterUser) {
        this.context = context;
        this.taskCompleteRegisterUser = taskCompleteRegisterUser;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Registrandote", "Espere un momento", true);

    }

    @Override
    protected Boolean doInBackground(User... params) {
        User data = params[0];
        boolean response=false;
        HttpURLConnection clientHttp=null;
        OutputStream outputStream=null;


        try{

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(URLEncoder.encode("user", "UTF-8"));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(data.getUser(), "UTF-8"));
            stringBuilder.append("&");
            stringBuilder.append(URLEncoder.encode("email", "UTF-8"));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(data.getEmail(), "UTF-8"));
            stringBuilder.append("&");
            stringBuilder.append(URLEncoder.encode("password", "UTF-8"));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(data.getPassword(), "UTF-8"));


            URL url=new URL("https://paginadejoseieshna.000webhostapp.com/Pages/user/crear.php");
            //Establecemos la conexion a la url
            clientHttp = (HttpURLConnection) url.openConnection();
            //Activamos el método post
            clientHttp.setRequestMethod("POST");
            clientHttp.setDoInput(true);
            //fijamos el tamaño
            clientHttp.setFixedLengthStreamingMode(stringBuilder.toString().getBytes().length);
            //cifrado de los datos
            clientHttp.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            //Con este bloque de codigo pasamos los datos al servicio web
            outputStream= new BufferedOutputStream(clientHttp.getOutputStream());
            outputStream.write(stringBuilder.toString().getBytes());
            outputStream.flush();
            outputStream.close();
            clientHttp.disconnect();
            response=true;

        }catch (Exception ex){
           ex.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(Boolean res) {
        super.onPostExecute(res);
        progressDialog.dismiss();
        taskCompleteRegisterUser.TaskCompleteRegisterUser(res);
    }

    public interface TaskCompleteRegisterUser{
        public void TaskCompleteRegisterUser(boolean estate);
    }
}
