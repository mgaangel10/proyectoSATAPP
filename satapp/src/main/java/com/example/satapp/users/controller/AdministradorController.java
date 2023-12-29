package com.example.satapp.users.controller;

import com.example.satapp.security.jwt.JwtProvider;
import com.example.satapp.users.Dto.JwtUserResponse;
import com.example.satapp.users.Dto.PostCrearUserDto;
import com.example.satapp.users.Dto.PostLogin;
import com.example.satapp.users.Dto.PostRegistroDto;
import com.example.satapp.users.model.Administrador;
import com.example.satapp.users.model.User;
import com.example.satapp.users.model.Usuario;
import com.example.satapp.users.service.AdministradorService;
import com.example.satapp.users.service.UsuarioService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdministradorController {

    private final AdministradorService administradorService;
    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    @Operation(summary = "Crear un administrador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado un administrador correctamente",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostCrearUserDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                       {"id":"e3ccd792-2ceb-44ad-97df-6a660d67e01c",
                                                       "fullname":"aa perez",
                                                       "username":"perez@gmail.cm",
                                                       "email":"perez@gmail.cm",
                                                       "creadoen":"2023-12-29T17:04:20.4570273"}
                                                    },
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content)
    })
    @PostMapping("auth/register/admin")
    public ResponseEntity<?> crearAdministrador(@RequestBody PostCrearUserDto postCrearUserDto) {
        try {
            Administrador administrador = administradorService.createWithRole(postCrearUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(PostRegistroDto.Administrador(administrador));
        } catch (ResponseStatusException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());
        }
    }

    @Operation(summary = "Login de un administrador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha logeado un administrador correctamente",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostCrearUserDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                           "id": "e3ccd792-2ceb-44ad-97df-6a660d67e01c",
                                                                                                    "fullname": "aa perez",
                                                                                                    "username": "perez@gmail.cm",
                                                                                                    "email": "perez@gmail.cm",
                                                                                                    "creadoen": "2023-12-29T17:04:20.457027",
                                                                                                    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlM2NjZDc5Mi0y
                                                                                                    Y2ViLTQ0YWQtOTdkZi02YTY2MGQ2N2UwMWMiLCJpYXQiOjE3MDM5NTI2Mjh9.
                                                                                                    uLRxPcoKmn_bhWdIMe_MoUUElUw57Km3iJtdbKVkuOUeaXJfqBaGrEu2I6Ob1mIBJo8Z7eQYzItUw3Eh7Cq3XQ"
                                                    },
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED",
                    content = @Content)
    })
    @PostMapping("auth/login/admin")
    public ResponseEntity<JwtUserResponse> loginadmin(@RequestBody PostLogin postLogin){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        postLogin.email(),
                        postLogin.password()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        Administrador administrador = (Administrador) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JwtUserResponse.ofAdminsitrador(administrador, token));
    }
    @Operation(summary = "Listar usuarios registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha listado los usuarios registrados ",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostCrearUserDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                       "id": "d8ff9f44-cf2c-4ff2-9d37-1b247ab85196",
                                                                                                        "email": "perez@gmail.cm",
                                                                                                        "username": "perez@gmail.cm",
                                                                                                        "password": "{bcrypt}$2a$10$3.bG4GL1Z4IZ/EBZHHyFH.X/YGEmE/PjxAu3dp6VBybXuPyDe3Z8a",
                                                                                                        "name": "aa",
                                                                                                        "lastName": "perez",
                                                                                                        "phoneNumber": null,
                                                                                                        "birthDate": null,
                                                                                                        "dni": "216252B",
                                                                                                        "fotoUrl": null,
                                                                                                        "accountNonExpired": true,
                                                                                                        "accountNonLocked": true,
                                                                                                        "credentialsNonExpired": true,
                                                                                                        "enabled": false,
                                                                                                        "roles": [
                                                                                                            "USER"
                                                                                                        ],
                                                                                                        "createdAt": "2023-12-29T17:13:37.43105",
                                                                                                        "lastPasswordChangeAt": "2023-12-29T17:13:37.426117",
                                                                                                        "usuarios": [],
                                                                                                        "inChargeof": [],
                                                                                                        "authorities": [
                                                                                                            {
                                                                                                                "authority": "ROLE_USER"
                                                                                                            }
                                                                                                        ]
                                                    },
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "Not found",
                    content = @Content)
    })
    @GetMapping("/administrador/usuarios/registrados")
    public ResponseEntity<List<Usuario>> usuariosRegistrados() {
        try {
            List<Usuario> users = administradorService.usuariosRegistrados();
            return ResponseEntity.ok(users);
        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @Operation(summary = "Validar la cuenta a una usuario despues de haberse registrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha validado correctamnete",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostCrearUserDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                      
                                                    },
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED",
                    content = @Content)
    })
    @PutMapping("/administrador/activar/usuario/{email}")
    public ResponseEntity<String> activarUsuario(@PathVariable String email) {
        try {
            administradorService.setearEneable(email);
            return ResponseEntity.ok("Usuario activado correctamente.");
        } catch (RuntimeException e) {
            // Si se lanza una excepción, enviamos un mensaje de error al cliente.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
