package com.example.satapp.Ticket.Repositorio;

import com.example.satapp.Ticket.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepo extends JpaRepository<Ticket, UUID> {
}
