package com.example.satapp.Ticket.dto;

import lombok.Builder;

@Builder
public record PutAsignarTecnico(String nombre,
                                String descripcion,
                                String dispositivo,
                                String estado,
                                String nombreTecnico) {
}
