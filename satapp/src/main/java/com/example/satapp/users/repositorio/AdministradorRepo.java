package com.example.satapp.users.repositorio;

import com.example.satapp.users.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdministradorRepo extends JpaRepository<Administrador, UUID> {
    Optional<Administrador> findFirstByEmail(String email);

}
