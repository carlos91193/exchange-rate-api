package com.grupocorin.alfincorp.exchange_rateapi.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
