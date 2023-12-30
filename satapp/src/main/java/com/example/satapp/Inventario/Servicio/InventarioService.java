package com.example.satapp.Inventario.Servicio;

import com.example.satapp.Inventario.Dto.GetListinventario;
import com.example.satapp.Inventario.Dto.PostCrearInventarioDTO;
import com.example.satapp.Inventario.Model.Estado;
import com.example.satapp.Inventario.Model.Inventario;
import com.example.satapp.Inventario.Repositorio.InventarioRepo;
import lombok.RequiredArgsConstructor;
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El nombre del inventario ya existe");
        }else {
            Inventario inventario = Inventario.builder()
                    .nombre(postCrearInventarioDTO.nombre())
                    .modelo(postCrearInventarioDTO.modelo())
                    .ubicacion(postCrearInventarioDTO.ubicacion())

                    .descripcion(postCrearInventarioDTO.descripcion())
                    .fechaCompra(LocalDate.now())
                    .precio(postCrearInventarioDTO.precio())
                    .fechaRegistro(LocalDateTime.now())
                    .build();
            return inventarioRepo.save(inventario);
        }
    }

    public List<GetListinventario> lidtarinventario(){
        List<GetListinventario> getListinventarios = inventarioRepo.getlist();
        if (getListinventarios.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No hay inventariois disponibles");
        }else {
            return inventarioRepo.getlist();
        }

    }

    public Optional<GetListinventario> finByNombre(String nombre){
        Optional<GetListinventario> getListinventario = inventarioRepo.finByNombre(nombre);
        if (getListinventario.isPresent()){
            return inventarioRepo.finByNombre(nombre);
        }else {
            throw new RuntimeException("no existe");
        }

    }

}
