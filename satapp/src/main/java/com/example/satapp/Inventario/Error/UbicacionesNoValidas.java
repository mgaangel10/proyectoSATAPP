package com.example.satapp.Inventario.Error;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class UbicacionesNoValidas extends RuntimeException{
    private HttpStatus httpStatus;
    private String mensaje;

    public UbicacionesNoValidas (HttpStatus httpStatus,String mensaje){
        super(mensaje);
        this.httpStatus=httpStatus;
        this.mensaje=mensaje;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
    public String getMensaje(){
        return mensaje;
    }
}
