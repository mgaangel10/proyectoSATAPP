package com.example.satapp.Inventario.Dto;

import com.example.satapp.Inventario.Model.Tipo;
import com.example.satapp.Inventario.Model.Ubicaciones;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record GetListinventario(String nombre,
                                String modelo,

                                //String estado,
                                String descripcion,

                                Tipo tipo,
                                Ubicaciones ubicaciones,
                                double precio) {
}
