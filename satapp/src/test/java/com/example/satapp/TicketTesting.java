package com.example.satapp;

import com.example.satapp.Ticket.Model.Ticket;
import com.example.satapp.Ticket.Repositorio.TicketRepo;
import com.example.satapp.Ticket.Service.TicketService;
import com.example.satapp.Ticket.dto.PutAsignarTecnico;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TicketTesting {

    @Mock
    private TicketRepo ticketRepo;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void asignarTecnico() {
        Ticket ticketEjemplo = new Ticket(UUID.fromString("678ab453-0e56-4974-be3d-774a824b863f"),
                "ticket", "fallo", "impresora", "fallo", null);

        Mockito.when(ticketRepo.findByNombreIgnoreCase("ticket")).thenReturn(Optional.of(ticketEjemplo));
        Mockito.when(ticketRepo.save(Mockito.any(Ticket.class))).thenReturn(ticketEjemplo);

        PutAsignarTecnico putAsignarTecnico = PutAsignarTecnico.builder()
                .nombre("ticket")
                .descripcion("nueva descripción")
                .dispositivo("nuevo dispositivo")
                .estado("nuevo estado")
                .nombreTecnico("nuevo técnico")
                .build();

        Ticket ticket = ticketService.asignarTecnico("ticket", putAsignarTecnico);

        assertEquals("nuevo técnico", ticket.getNombreTecnico());
    }
}
