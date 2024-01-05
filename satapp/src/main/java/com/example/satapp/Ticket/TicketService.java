package com.example.satapp.Ticket;

import com.example.satapp.Ticket.Model.Ticket;
import com.example.satapp.Ticket.Repositorio.TicketRepo;
import com.example.satapp.Ticket.dto.PostCrearTicketDto;
import com.example.satapp.users.Dto.PostCrearUserDto;
import com.example.satapp.users.Dto.PostLogin;
import com.example.satapp.users.model.Usuario;
import com.example.satapp.users.repositorio.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepo ticketRepo;
    private final AuditorAware<String> auditorAware;
    private final UsuarioRepo usuarioRepo;



    public Ticket crearTicket(PostCrearTicketDto postCrearTicketDto) {

        Ticket ticket = Ticket.builder()
                .descripcion(postCrearTicketDto.descripcion())
                .estado(postCrearTicketDto.estado())
                .dispositivo(postCrearTicketDto.dispositivo())

                .build();


        return ticketRepo.save(ticket);
    }

}




