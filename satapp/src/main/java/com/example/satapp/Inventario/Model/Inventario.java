package com.example.satapp.Inventario.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Inventario {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",type = org.hibernate.id.UUIDGenerator.class)
    private UUID id;

   @Column(name = "nombre")
    private String nombre;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "ubicacion")
    private String ubicacion;

    @ElementCollection(fetch = FetchType.EAGER)
    private EnumSet<Estado> estados;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_Compra")
    private LocalDate fechaCompra;

    @Column(name = "precio")
    private double precio;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Tipo> tipos;

    @Column(name = "fecha_Registro")
    private LocalDateTime fechaRegistro;
}
