package com.api.picpay.dto;

public record TransactionDTO(Long payer, Long payee, Double value) {
}
