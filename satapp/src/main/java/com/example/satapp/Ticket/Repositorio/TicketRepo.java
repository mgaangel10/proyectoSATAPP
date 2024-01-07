package com.example.satapp.Ticket.Repositorio;

import com.example.satapp.Inventario.Dto.GetListinventario;
import com.example.satapp.Inventario.Model.Inventario;
import com.example.satapp.Ticket.Model.Ticket;
import com.example.satapp.Ticket.dto.GetListTicketsDto;
import com.example.satapp.Ticket.dto.PutAsignarTecnico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketRepo extends JpaRepository<Ticket, UUID> {

    Optional<Ticket> findByNombreIgnoreCase(String nombre);

    @Query("""
            select new com.example.satapp.Ticket.dto.PutAsignarTecnico(
            t.nombre,
            t.descripcion,
            t.estado,
            t.dispositivo,
            t.nombreTecnico
            
            )
            from Ticket t
            """)
    Page<PutAsignarTecnico> getlist(Pageable pageable);

}
