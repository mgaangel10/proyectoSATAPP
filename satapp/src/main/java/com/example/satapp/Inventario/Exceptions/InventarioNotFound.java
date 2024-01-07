package com.example.satapp.Inventario.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;
import java.time.Instant;

public class InventarioNotFound extends ErrorResponseException {

    public InventarioNotFound (){
        super(HttpStatus.NOT_FOUND, of("Inventario no encontrado"),null);
    }

    public static ProblemDetail of(String mensage){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,mensage);
        problemDetail.setTitle("No se ha encontrado el inventario");
        problemDetail.setType(URI.create("https://api.midominio.com/errors/inventario-not-found"));
        problemDetail.setProperty("entityType","Note");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
