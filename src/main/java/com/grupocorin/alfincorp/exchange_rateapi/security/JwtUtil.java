package com.grupocorin.alfincorp.exchange_rateapi.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 🔐 Clave secreta con al menos 32 caracteres (256 bits para HS256)
    private final String SECRET_KEY = "mi_clave_supersecreta_para_firma_segura_1234";

    // 🕐 1 hora de expiración
    private final long EXPIRATION_TIME = 1000 * 60 * 60;

    // 🔏 Generar token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔓 Validar token
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // 👤 Extraer username (subject)
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // 🔍 Extraer claims (interno)
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 🔑 Obtener clave para firmar/validar
    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}