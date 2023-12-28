package com.example.satapp.users.controller;

import com.example.satapp.security.jwt.JwtProvider;
import com.example.satapp.users.Dto.PostCrearUserDto;
import com.example.satapp.users.Dto.PostRegistroDto;
import com.example.satapp.users.model.Administrador;
import com.example.satapp.users.model.Usuario;
import com.example.satapp.users.service.UsuarioService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("auth/register/user")
    public ResponseEntity<PostRegistroDto> crearUser(@RequestBody PostCrearUserDto postCrearUserDto){
        Usuario usuario = usuarioService.createWithRole(postCrearUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostRegistroDto.Usuario(usuario));
    }
}
