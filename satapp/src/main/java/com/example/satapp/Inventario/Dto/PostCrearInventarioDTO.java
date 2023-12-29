package com.example.satapp.Inventario.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PostCrearInventarioDTO(String id,
                                     String nombre,
                                     String modelo,
                                     String ubicacion,
                                     //String estado,
                                     String descripcion,
                                     LocalDate fechaCompra,
                                     double precio,
                                     LocalDateTime fechaRegistro
                                      ){
}
