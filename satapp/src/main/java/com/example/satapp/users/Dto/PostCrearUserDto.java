package com.example.satapp.users.Dto;

import java.time.LocalDate;

public record PostCrearUserDto(String email,
                               String name,
                               String lastName,
                               String password,
                               String dni,
                               LocalDate nacimiento) {
}
