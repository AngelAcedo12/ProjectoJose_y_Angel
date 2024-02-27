package com.example.projectojose_y_angel;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.projectojose_y_angel.services.User.InsertarUsuario;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws InterruptedException {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.projectojose_y_angel", appContext.getPackageName());

        String dato = "user="+"jose"+
                "&"+"password="+"123"+
                "&"+"email="+"jose@gmail.com";

        InsertarUsuario insertarUsuario = new InsertarUsuario();
        insertarUsuario.execute(dato);
        Thread.sleep(1000);


    }
}