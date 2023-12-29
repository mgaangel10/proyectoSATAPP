package com.example.satapp.users.Dto;

import com.example.satapp.users.model.Administrador;
import com.example.satapp.users.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PostRegistroDto  {
    String id;
    String fullname;
    String username;
    String email;


    LocalDateTime creadoen;

            public static PostRegistroDto Usuario(Usuario usuario){
                return PostRegistroDto.builder()
                        .id(usuario.getId().toString())
                        .fullname(usuario.getName().concat(" "+usuario.getLastName()))
                        .username(usuario.getUsername())
                        .email(usuario.getEmail())

                        .creadoen(usuario.getCreatedAt())
                        .build();
            }
            public static PostRegistroDto Administrador(Administrador administrador){
                return PostRegistroDto.builder()
                        .id(administrador.getId().toString())
                        .fullname(administrador.getName().concat(" "+administrador.getLastName()))
                        .username(administrador.getUsername())
                        .email(administrador.getEmail())

                        .creadoen(administrador.getCreatedAt())
                        .build();
            }
}
