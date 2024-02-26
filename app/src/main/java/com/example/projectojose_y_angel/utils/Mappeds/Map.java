package com.example.projectojose_y_angel.utils.Mappeds;


import java.io.IOException;

/*

   IMPORTANTE :  Entra T y sale S

 */
public interface Map<T,S> {

    public S map(T t) throws IOException;
}
