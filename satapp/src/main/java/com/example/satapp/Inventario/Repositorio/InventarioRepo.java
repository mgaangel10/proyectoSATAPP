package com.example.satapp.Inventario.Repositorio;

import com.example.satapp.Inventario.Dto.GetListinventario;
import com.example.satapp.Inventario.Model.Inventario;
import com.example.satapp.Inventario.Model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventarioRepo extends JpaRepository<Inventario, UUID> {
    boolean existsByNombreIgnoreCase(String nombre);

    @Query("""
            select new com.example.satapp.Inventario.Dto.GetListinventario(
            i.nombre,
            i.modelo,
            i.ubicacion,
            i.descripcion,
            i.tipos,
            i.precio
            )
            from Inventario i
            """)
    List<GetListinventario> getlist();

    @Query("""
            select new com.example.satapp.Inventario.Dto.GetListinventario(
            i.nombre,
            i.modelo,
            i.ubicacion,
            i.descripcion,
            i.tipos,
            i.precio
            )
            from Inventario i
            where i.nombre = ?1
            """)
    Optional<GetListinventario> finByNombre(String nombre);

    @Query("""
            select new com.example.satapp.Inventario.Dto.GetListinventario(
            i.nombre,
            i.modelo,
            i.ubicacion,
            i.descripcion,
            i.tipos,
            i.precio
            )
            from Inventario i
            where :tipo member of i.tipos
            """)
    List<GetListinventario> getlistPorTipos(Tipo tipo);


}
