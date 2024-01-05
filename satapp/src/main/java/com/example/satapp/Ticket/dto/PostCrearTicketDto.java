package com.example.satapp.Ticket.dto;

import com.example.satapp.Ticket.Model.Ticket;
import com.example.satapp.users.Dto.PostCrearUserDto;
import com.example.satapp.users.Dto.PostLogin;
import com.example.satapp.users.model.User;
import com.example.satapp.users.model.Usuario;
import lombok.Builder;

public record PostCrearTicketDto(String descripcion,
                                 String dispositivo,
                                 String estado,
                                 String nombreUsuario


) {



}
