package com.api.picpay.dto;

public record CommonUserDTO(String fullName, String CPF, String email, String password, Double balance) {
}
