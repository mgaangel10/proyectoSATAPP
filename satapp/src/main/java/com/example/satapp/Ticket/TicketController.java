package com.example.satapp.Ticket;

import com.example.satapp.Ticket.Model.Ticket;
import com.example.satapp.Ticket.dto.GetListTicketsDto;
import com.example.satapp.Ticket.dto.PostCrearTicketDto;
import com.example.satapp.Ticket.dto.PutAsignarTecnico;
import com.example.satapp.users.Dto.PostCrearUserDto;
import com.example.satapp.users.Dto.PostLogin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.UserSessionEvent;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @Operation(summary = "crear un ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado el ticket",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostCrearUserDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                        "id": "dacd95cf-82c6-4750-a8cb-b6780c925119",
                                                           "descripcion": "hola hola hola",
                                                           "dispositivo": "general",
                                                           "estado": "HP EliteBook 840 G6 
                                                    },
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "401",
                    description = "Bad request",
                    content = @Content)
    })
    @PostMapping("usuario/crear/ticket")
    public ResponseEntity<?> crearTicket(@RequestBody PostCrearTicketDto postCrearTicketDto) {
        Ticket ticket = ticketService.crearTicket(postCrearTicketDto);
        return ResponseEntity.ok(ticket);
    }

    @Operation(summary = "editar un ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha editado el ticket",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostCrearUserDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                       "id": "ef4f54b7-46c8-47fe-9a73-908446c95442",
                                                                                              "nombre": "hola.2",
                                                                                              "descripcion": "hola hola hola",
                                                                                              "dispositivo": "general",
                                                                                              "estado": "HP EliteBook 840 G6"
                                                    },
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "401",
                    description = "Bad request",
                    content = @Content)
    })
    @PutMapping("usuario/editar/{nombre}")
    public ResponseEntity<?> editarTicket(@PathVariable String nombre, @RequestBody PostCrearTicketDto postCrearTicketDto) {
        Ticket ticket = ticketService.editarTicket(nombre, postCrearTicketDto);
        return ResponseEntity.ok(ticket);
    }

    @Operation(summary = "listar los tickets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha listado los tickets",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostCrearUserDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                       {
                                                                     "nombre": "hola.2",
                                                                     "descripcion": "hola hola hola",
                                                                     "dispositivo": "HP EliteBook 840 G6",
                                                                     "estado": "general"
                                                                                                                                 }
                                                    },
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "401",
                    description = "Bad request",
                    content = @Content)
    })
    @GetMapping("/administrador/listar/tickets")
    public ResponseEntity<List<PutAsignarTecnico>> listarTodo() {
        List<PutAsignarTecnico> getListTicketsDtos = ticketService.listarTodo();
        return ResponseEntity.ok(getListTicketsDtos);
    }
    @Operation(summary = "buscar ticket por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha buscado por nombre los tickets",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostCrearUserDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                       {
                                                                     "nombre": "hola.2",
                                                                     "descripcion": "hola hola hola",
                                                                     "dispositivo": "HP EliteBook 840 G6",
                                                                     "estado": "general"
                                                                                                                                 }
                                                    },
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "401",
                    description = "Bad request",
                    content = @Content)
    })
    @GetMapping("/administrador/buscar/ticket/{nombre}")
    public ResponseEntity<Optional<Ticket>> buscarTicketPorNombre(@PathVariable String nombre){
        Optional<Ticket> ticket = ticketService.buscarPorNombre(nombre);
        return ResponseEntity.ok(ticket);
    }

    @Operation(summary = "Asignar ticket a un tecnico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha asignado el ticket a un tecnico",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostCrearUserDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                       {
                                                                   "nombre": "hola",
                                                                    "descripcion": "hola hola hola",
                                                                    "dispositivo": "HP EliteBook 840 G6",
                                                                    "estado": "general",
                                                                    "nombreTecnico": "admin"
                                                                                                                                 }
                                                    },
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "401",
                    description = "Bad request",
                    content = @Content)
    })
    @PutMapping("/administrador/asignar/ticket/{nombre}")
    public ResponseEntity<?> asignarTecnico(@PathVariable String nombre, @RequestBody PutAsignarTecnico putAsignarTecnico){
        Ticket ticket = ticketService.asignarTecnico(nombre,putAsignarTecnico);
        return ResponseEntity.ok(ticket);
    }

}
