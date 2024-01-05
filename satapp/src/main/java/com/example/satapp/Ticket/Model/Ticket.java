package com.example.satapp.Ticket.Model;

import com.example.satapp.users.model.Administrador;
import com.example.satapp.users.model.User;
import com.example.satapp.users.model.Usuario;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Ticket {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",type = org.hibernate.id.UUIDGenerator.class)
    private UUID id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "dispositivo")
    private String dispositivo;

    @Column(name = "estado")
    private String estado;






}
