package com.example.satapp.users.service;

import com.example.satapp.users.Dto.PostCrearUserDto;
import com.example.satapp.users.model.Administrador;
import com.example.satapp.users.model.UserRoles;
import com.example.satapp.users.model.Usuario;
import com.example.satapp.users.repositorio.AdministradorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdministradorService {

    private final AdministradorRepo administradorRepo;
    private final PasswordEncoder passwordEncoder;
    public Optional<Administrador> findById(UUID id){return administradorRepo.findById(id);}
    public Optional<Administrador> findByEmail(String email) {
        return administradorRepo.findFirstByEmail(email);
    }

    public Administrador crearAdministrador(PostCrearUserDto postCrearUserDto ,EnumSet<UserRoles> userRoles){
    if (administradorRepo.existsByEmailIgnoreCase(postCrearUserDto.email())){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El email ya ha sido registrado");
    }
    Administrador administrador = Administrador.builder()
            .email(postCrearUserDto.email())
            .name(postCrearUserDto.name())
            .lastName(postCrearUserDto.lastName())
            .password(passwordEncoder.encode(postCrearUserDto.password()))
            .createdAt(LocalDateTime.now())
            .dni(postCrearUserDto.dni())
            .birthDate(postCrearUserDto.nacimiento())
            .roles(userRoles)
            .build();
    return administradorRepo.save(administrador);
    }

    public Administrador createWithRole(PostCrearUserDto postCrearUserDto){
        return crearAdministrador(postCrearUserDto,EnumSet.of(UserRoles.ADMINISTRADOR));
    }
}
