package com.example.satapp.Inventario.Controller;

import com.example.satapp.Inventario.Dto.PostCrearInventarioDTO;
import com.example.satapp.Inventario.Model.Inventario;
import com.example.satapp.Inventario.Servicio.InventarioService;
import com.example.satapp.users.Dto.PostCrearUserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;
    @Operation(summary = "Crear un inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado un inventario correctamente",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostCrearUserDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                       "id": "a5e308dd-0518-49a7-9a94-c389a2932c7f",
                                                                                                    "nombre": "Computadora",
                                                                                                    "modelo": "HP EliteBook 840 G6",
                                                                                                    "ubicacion": "Oficina 301",
                                                                                                    "estados": null,
                                                                                                    "descripcion": "Laptop para desarrollo con 16GB RAM y 512GB SSD",
                                                                                                    "fechaCompra": "2023-12-29",
                                                                                                    "precio": 1200.0,
                                                                                                    "fechaRegistro": "2023-12-29T21:20:34.5718554",
                                                                                                    "responsable": null
                                                    },
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content)
    })
    @PostMapping("/administrador/crear/inventario")
    public ResponseEntity<?> crearInventario(@RequestBody PostCrearInventarioDTO postCrearInventarioDTO) {
        try {
            Inventario inventario = inventarioService.crearInventario(postCrearInventarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(inventario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
