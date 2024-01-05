package com.example.satapp.Ticket;

import com.example.satapp.Ticket.Model.Ticket;
import com.example.satapp.Ticket.Repositorio.TicketRepo;
import com.example.satapp.Ticket.dto.GetListTicketsDto;
import com.example.satapp.Ticket.dto.PostCrearTicketDto;
import com.example.satapp.Ticket.dto.PutAsignarTecnico;
import com.example.satapp.users.Dto.PostCrearUserDto;
import com.example.satapp.users.Dto.PostLogin;
import com.example.satapp.users.model.Usuario;
import com.example.satapp.users.repositorio.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
                .nombre(postCrearTicketDto.nombre())
                .descripcion(postCrearTicketDto.descripcion())
                .estado(postCrearTicketDto.estado())
                .dispositivo(postCrearTicketDto.dispositivo())

                .build();


        return ticketRepo.save(ticket);
    }
    public List<PutAsignarTecnico> listarTodo(){

        List<PutAsignarTecnico> getListTicketsDtos = ticketRepo.getlist();
        if (getListTicketsDtos.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No hay tickets disponibles");
        }else {
            return ticketRepo.getlist();
        }
    }

    public Ticket editarTicket(String nombre,PostCrearTicketDto postCrearTicketDto){
        Optional<Ticket> ticket = ticketRepo.findByNombreIgnoreCase(nombre);
        if (ticket.isPresent()){
            return ticket.map(t->{
                t.setNombre(postCrearTicketDto.nombre());
                t.setDescripcion(postCrearTicketDto.descripcion());
                t.setDispositivo(postCrearTicketDto.dispositivo());
                t.setEstado(postCrearTicketDto.estado());
                return ticketRepo.save(t);
            }).orElse(null);
        }else {
            throw new RuntimeException("El ticket no se ha sido encontrado");
        }


    }
    public Ticket asignarTecnico(String nombre, PutAsignarTecnico putAsignarTecnico){
        Optional<Ticket> ticket = ticketRepo.findByNombreIgnoreCase(nombre);
        if (ticket.isPresent()){
            return ticket.map(t->{
                t.setNombre(putAsignarTecnico.nombre());
                t.setDescripcion(putAsignarTecnico.descripcion());
                t.setDispositivo(putAsignarTecnico.dispositivo());
                t.setEstado(putAsignarTecnico.estado());
                t.setNombreTecnico(putAsignarTecnico.nombreTecnico());
                return ticketRepo.save(t);
            }).orElse(null);
        }{
            throw new RuntimeException("El ticket no se ha sido encontrado");
        }
    }

    public Optional<Ticket> buscarPorNombre(String nombre){
        Optional<Ticket> ticket = ticketRepo.findByNombreIgnoreCase(nombre);
        if (ticket.isPresent()){
            return ticketRepo.findByNombreIgnoreCase(nombre);
        }else {
            throw new RuntimeException("no se han encontrado nun ticket");
        }
    }

}




