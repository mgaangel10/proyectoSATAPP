package com.example.satapp.Ticket.Error;

import org.springframework.http.HttpStatus;

public class TicketNoDisponibles extends RuntimeException{

    private HttpStatus httpStatus;
    private String mensaje;

    public TicketNoDisponibles (HttpStatus httpStatus,String mensaje){
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
