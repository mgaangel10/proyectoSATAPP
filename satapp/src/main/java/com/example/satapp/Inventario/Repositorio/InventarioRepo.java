package com.example.satapp.Inventario.Repositorio;

import com.example.satapp.Inventario.Dto.GetListinventario;
import com.example.satapp.Inventario.Model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface InventarioRepo extends JpaRepository<Inventario, UUID> {
    boolean existsByNombreIgnoreCase(String nombre);

    @Query("""
            select new com.example.satapp.Inventario.Dto.GetListinventario(
            i.nombre,
            i.modelo,
            i.ubicacion,
            i.descripcion,
            i.precio
            )
            from Inventario i
            """)
    List<GetListinventario> getlist();
}
