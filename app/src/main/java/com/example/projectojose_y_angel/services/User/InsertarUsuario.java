package com.example.projectojose_y_angel.services.User;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class InsertarUsuario extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        String data = params[0];

        HttpURLConnection clientHttp=null;
        OutputStream outputStream=null;
        try{
            //Objeto url con la direccion del servicio web de insertar
            //URL url=new URL("https://paginadejoseieshna.000webhostapp.com/Pages/user/crear.php");
            URL url=new URL("http://192.168.1.10:80/Pages/user/crear.php");
            //Establecemos la conexion a la url
            clientHttp = (HttpURLConnection) url.openConnection();
            //Activamos el método post
            clientHttp.setRequestMethod("POST");
            clientHttp.setDoInput(true);
            //fijamos el tamaño
            clientHttp.setFixedLengthStreamingMode(data.getBytes().length);
            //cifrado de los datos
            clientHttp.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            //Con este bloque de codigo pasamos los datos al servicio web
            outputStream= new BufferedOutputStream(clientHttp.getOutputStream());
            outputStream.write(data.getBytes());
            outputStream.flush();
            outputStream.close();


        }catch (Exception ex){
            Log.i("MyLog",ex.getMessage());
        }
        return null;
    }
}
