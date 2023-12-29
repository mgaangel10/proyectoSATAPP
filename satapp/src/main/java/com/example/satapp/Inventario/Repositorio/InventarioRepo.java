package com.example.satapp.Inventario.Repositorio;

import com.example.satapp.Inventario.Model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventarioRepo extends JpaRepository<Inventario, UUID> {
    boolean existsByNombreIgnoreCase(String nombre);
}
