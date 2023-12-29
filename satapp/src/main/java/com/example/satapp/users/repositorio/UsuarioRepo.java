package com.example.satapp.users.repositorio;

import com.example.satapp.users.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepo extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findFirstByEmail(String email);
    boolean existsByEmailIgnoreCase(String email);
    List<Usuario> findByEnabledFalse();
    Optional<Usuario> findByEmail(String email);

}
