package com.api.picpay.dto;

public record UserDTO(String fullName, String CPF, String email, String password, Double balance, RoleEnum role) {
}
