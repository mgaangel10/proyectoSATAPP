package com.example.satapp.users.Dto;

import com.example.satapp.users.model.UserRoles;

import java.time.LocalDate;
import java.util.EnumSet;

public record PostCrearUserDto(String email,
                               String name,
                               String lastName,
                               String password,
                               String dni,
                               LocalDate nacimiento) {
}
