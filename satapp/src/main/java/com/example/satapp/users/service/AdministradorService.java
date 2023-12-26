package com.example.satapp.users.service;

import com.example.satapp.users.model.Administrador;
import com.example.satapp.users.model.Usuario;
import com.example.satapp.users.repositorio.AdministradorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdministradorService {

    private final AdministradorRepo administradorRepo;
    public Optional<Administrador> findById(UUID id){return administradorRepo.findById(id);}
    public Optional<Administrador> findByEmail(String email) {
        return administradorRepo.findFirstByEmail(email);
    }
}
