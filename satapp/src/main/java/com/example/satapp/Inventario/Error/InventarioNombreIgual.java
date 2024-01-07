package com.example.satapp.Inventario.Error;

import org.springframework.http.HttpStatus;

public class InventarioNombreIgual extends RuntimeException{
    private HttpStatus httpStatus;
    private String mensaje;

    public InventarioNombreIgual(HttpStatus httpStatus, String mensaje){
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
