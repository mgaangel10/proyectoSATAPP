package com.example.satapp.Inventario.Repositorio;

import com.example.satapp.Inventario.Dto.GetListinventario;
import com.example.satapp.Inventario.Model.Inventario;
import com.example.satapp.Inventario.Model.Tipo;
import com.example.satapp.Inventario.Model.Ubicaciones;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventarioRepo extends JpaRepository<Inventario, UUID> {
    boolean existsByNombreIgnoreCase(String nombre);
    Optional<Inventario> findByNombreIgnoreCase(String nombre);

    @Query("""
            select new com.example.satapp.Inventario.Dto.GetListinventario(
            i.nombre,
            i.modelo,
            i.descripcion,
            i.tipos,
            i.ubicaciones,
            i.precio
            )
            from Inventario i
            """)
    Page<GetListinventario> getlist(Pageable pageable);

    @Query("""
            select new com.example.satapp.Inventario.Dto.GetListinventario(
            i.nombre,
            i.modelo,
            
            i.descripcion,
            i.tipos,
            i.ubicaciones,
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
            
            i.descripcion,
            i.tipos,
            i.ubicaciones,
            i.precio
            )
            from Inventario i
            where :tipo member of i.tipos
            """)
    List<GetListinventario> getlistPorTipos(Tipo tipo);
    @Query("""
            select new com.example.satapp.Inventario.Dto.GetListinventario(
            i.nombre,
            i.modelo,
            
            i.descripcion,
            i.tipos,
            i.ubicaciones,
            i.precio
            )
            from Inventario i
            where :ubicaciones member of i.ubicaciones
            """)
    List<GetListinventario> getlistPorubicaciones(Ubicaciones ubicaciones);


}
