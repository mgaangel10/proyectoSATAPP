package com.example.satapp.users.controller;

import com.example.satapp.security.jwt.JwtProvider;
import com.example.satapp.users.Dto.PostCrearUserDto;
import com.example.satapp.users.Dto.PostRegistroDto;
import com.example.satapp.users.model.Administrador;
import com.example.satapp.users.service.AdministradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdministradorController {

    private final AdministradorService administradorService;

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("auth/register/admin")
    public ResponseEntity<PostRegistroDto> crearAdminsitrador(@RequestBody PostCrearUserDto postCrearUserDto){
        Administrador administrador = administradorService.createWithRole(postCrearUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(PostRegistroDto.Administrador(administrador));
    }

}
