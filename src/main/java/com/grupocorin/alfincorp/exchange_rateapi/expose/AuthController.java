package com.grupocorin.alfincorp.exchange_rateapi.expose;

import com.grupocorin.alfincorp.exchange_rateapi.models.dto.AuthRequest;
import com.grupocorin.alfincorp.exchange_rateapi.models.dto.AuthResponse;
import com.grupocorin.alfincorp.exchange_rateapi.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.grupocorin.alfincorp.exchange_rateapi.models.constants.ApplicationConstants.PASSWORD;
import static com.grupocorin.alfincorp.exchange_rateapi.models.constants.ApplicationConstants.USERNAME;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Mono<AuthResponse> login(@RequestBody AuthRequest request) {
        if (USERNAME.equals(request.getUsername()) && PASSWORD.equals(request.getPassword())) {
            String token = jwtUtil.generateToken(request.getUsername());
            return Mono.just(new AuthResponse(token));
        } else {
            return Mono.error(new RuntimeException("Credenciales inválidas"));
        }
    }
}
