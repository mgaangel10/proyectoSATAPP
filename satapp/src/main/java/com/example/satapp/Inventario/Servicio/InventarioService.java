package com.example.satapp.Inventario.Servicio;

import com.example.satapp.Inventario.Dto.GetListinventario;
import com.example.satapp.Inventario.Dto.PostCrearInventarioDTO;
import com.example.satapp.Inventario.Error.InventarioNombreIgual;
import com.example.satapp.Inventario.Error.TipoNoValidos;
import com.example.satapp.Inventario.Error.UbicacionesNoValidas;
import com.example.satapp.Inventario.Exceptions.InventarioNotFound;
import com.example.satapp.Inventario.Model.Estado;
import com.example.satapp.Inventario.Model.Inventario;
import com.example.satapp.Inventario.Model.Tipo;
import com.example.satapp.Inventario.Model.Ubicaciones;
import com.example.satapp.Inventario.Repositorio.InventarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepo inventarioRepo;

    public Inventario crearInventario(PostCrearInventarioDTO postCrearInventarioDTO ){
        if (inventarioRepo.existsByNombreIgnoreCase(postCrearInventarioDTO.nombre())){
            throw new InventarioNombreIgual(HttpStatus.BAD_REQUEST,"El nombre del inventario ya existe");
        }else {
            Ubicaciones ubicaciones = Ubicaciones.valueOf(postCrearInventarioDTO.ubicaciones().toUpperCase());
            if (ubicaciones==null){
                throw new UbicacionesNoValidas(HttpStatus.BAD_REQUEST, "Ubicaciones de inventario no válido");
            }
            Tipo tipoEnum = Tipo.valueOf(postCrearInventarioDTO.tipo().toUpperCase());
            if (tipoEnum == null) {
                throw new TipoNoValidos(HttpStatus.BAD_REQUEST, "Tipo de inventario no válido");
            }

            Inventario inventario = Inventario.builder()
                    .nombre(postCrearInventarioDTO.nombre())
                    .modelo(postCrearInventarioDTO.modelo())
                    .descripcion(postCrearInventarioDTO.descripcion())
                    .fechaCompra(LocalDate.now())
                    .tipos(EnumSet.of(tipoEnum))
                    .ubicaciones(EnumSet.of(ubicaciones))
                    .precio(postCrearInventarioDTO.precio())
                    .fechaRegistro(LocalDateTime.now())
                    .build();
            return inventarioRepo.save(inventario);
        }
    }

    public Page<GetListinventario> lidtarinventario(Pageable pageable){
        Page<GetListinventario> getListinventarios = inventarioRepo.getlist(pageable);
        if (getListinventarios.isEmpty()){
            throw new InventarioNotFound();
        }else {
            return inventarioRepo.getlist(pageable);
        }

    }

    public Optional<GetListinventario> finByNombre(String nombre){
        Optional<GetListinventario> getListinventario = inventarioRepo.finByNombre(nombre);
        if (getListinventario.isPresent()){
            return inventarioRepo.finByNombre(nombre);
        }else {
            throw new InventarioNotFound();
        }

    }

    public List<GetListinventario> findPorTipos(Tipo tipo){
        List<GetListinventario> getListinventarios = inventarioRepo.getlistPorTipos(tipo);
        if (getListinventarios.isEmpty()){
            throw new TipoNoValidos(HttpStatus.NOT_FOUND,"no se ha encontrado el inventario por el tipo "+tipo);
        }else {
            return inventarioRepo.getlistPorTipos(tipo);
        }

    }

    public List<GetListinventario> findPorUbicaiones(Ubicaciones ubicaciones){
        List<GetListinventario> getListinventarios = inventarioRepo.getlistPorubicaciones(ubicaciones);
        if (getListinventarios.isEmpty()){
            throw new UbicacionesNoValidas(HttpStatus.NOT_FOUND,"no se ha encontrado el inventario por la ubicacion "+ubicaciones);
        }else {
            return inventarioRepo.getlistPorubicaciones(ubicaciones);
        }

    }

    public Inventario editarInventario(String nombre,PostCrearInventarioDTO postCrearInventarioDTO){
        Optional<Inventario> inventario = inventarioRepo.findByNombreIgnoreCase(nombre);
        if (inventario.isPresent()){
            Ubicaciones ubicaciones = Ubicaciones.valueOf(postCrearInventarioDTO.ubicaciones().toUpperCase());
            if (ubicaciones==null){
                throw new UbicacionesNoValidas(HttpStatus.BAD_REQUEST, "Ubicaciones de inventario no válido");
            }
            Tipo tipoEnum = Tipo.valueOf(postCrearInventarioDTO.tipo().toUpperCase());
            if (tipoEnum == null) {
                throw new TipoNoValidos(HttpStatus.BAD_REQUEST, "Tipo de inventario no válido");
            }


            return inventario.map(i->{
                i.setNombre(postCrearInventarioDTO.nombre());
                i.setModelo(postCrearInventarioDTO.modelo());
                i.setDescripcion(postCrearInventarioDTO.descripcion());
                i.setFechaCompra(postCrearInventarioDTO.fechaCompra());
                i.setTipos(EnumSet.of(tipoEnum));
                i.setUbicaciones(EnumSet.of(ubicaciones));
                i.setPrecio(postCrearInventarioDTO.precio());
                return inventarioRepo.save(i);
            }).orElse(null);
        }else {
            throw new InventarioNotFound();
        }
    }

    public void eliminarInventario(String nombre) {
        Optional<Inventario> inventarioOptional = inventarioRepo.findByNombreIgnoreCase(nombre);
        if (inventarioOptional.isPresent()) {
            inventarioRepo.delete(inventarioOptional.get());
        } else {
            throw new InventarioNotFound();
        }
    }




}
