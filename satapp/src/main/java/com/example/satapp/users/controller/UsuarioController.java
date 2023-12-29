package com.example.satapp.users.controller;

import com.example.satapp.security.jwt.JwtProvider;
import com.example.satapp.users.Dto.JwtUserResponse;
import com.example.satapp.users.Dto.PostCrearUserDto;
import com.example.satapp.users.Dto.PostLogin;
import com.example.satapp.users.Dto.PostRegistroDto;
import com.example.satapp.users.model.Administrador;
import com.example.satapp.users.model.Usuario;
import com.example.satapp.users.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    @Operation(summary = "Crear un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado correctamente un usuario",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostCrearUserDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                       "id": "43126860-c676-4c12-a79d-184a4de1388e",
                                                          "fullname": "aa perez",
                                                          "username": "perez@gmail.cmm2m",
                                                          "email": "perez@gmail.cmm2m",
                                                          "creadoen": "2023-12-29T17:18:52.6635137"
                                                    },
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "401",
                    description = "UNAUTHORIZED",
                    content = @Content)
    })
    @PostMapping("auth/register/user")
    public ResponseEntity<?> crearUser(@RequestBody PostCrearUserDto postCrearUserDto) {
        try {
            Usuario usuario = usuarioService.createWithRole(postCrearUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(PostRegistroDto.Usuario(usuario));
        } catch (ResponseStatusException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());
        }
    }


    @Operation(summary = "Login de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha logeado un usuario correctamente",
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
    @PostMapping("auth/login/user")
    public ResponseEntity<JwtUserResponse> loginUser(@RequestBody PostLogin postLogin){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        postLogin.email(),
                        postLogin.password()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JwtUserResponse.ofUsuario(usuario, token));
    }
}
