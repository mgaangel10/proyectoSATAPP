package com.example.satapp.users.service;

import com.example.satapp.users.model.Administrador;
import com.example.satapp.users.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Primary
@Service("patientDetailsService")
@RequiredArgsConstructor
public class CustomDetailUserService implements UserDetailsService {

    private final UsuarioService patientService;
    private final AdministradorService sanitaryService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> patient = patientService.findByEmail(email);
        Optional<Administrador> sanitary = sanitaryService.findByEmail(email);

        if (patient.isPresent()) {
            return patient.get();
        } else {
            if (sanitary.isPresent()) {
                return sanitary.get();
            } else {
                throw (new UsernameNotFoundException("No user with email: " + email));
            }
        }
                /*
                .orElseThrow(() -> new UsernameNotFoundException("No user with email: " +  email));
                */
    }
}

