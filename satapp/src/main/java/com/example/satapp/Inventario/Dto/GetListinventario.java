package com.example.satapp.Inventario.Dto;

import java.time.LocalDate;

public record GetListinventario(String nombre,
                                String modelo,
                                String ubicacion,
                                //String estado,
                                String descripcion,

                                double precio) {
}
