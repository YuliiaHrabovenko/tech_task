package com.tech.task.report.security;

import com.tech.task.report.exceptions.AuthException;
import com.tech.task.report.exceptions.ExceptionUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public final class JwtService {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expiration;
    public static final int BEGIN_INDEX = 7;
    public static final String BEARER = "Bearer";

    public String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);
        Claims claims = Jwts.claims().add("role", authentication.getAuthorities()).build();
        return Jwts.builder()
                .subject(authentication.getName())
                .claims(claims)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    public SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims validateToken(String token) {
        Claims claims;
        try {
            claims = Jwts
                    .parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException | IllegalArgumentException e) {
            throw new AuthException(ExceptionUtil.INVALID_TOKEN_EXCEPTION);
        }
        if (claims.getExpiration().before(new Date())) {
            throw new AuthException(ExceptionUtil.EXPIRED_TOKEN_EXCEPTION);
        }
        return claims;
    }

    public String validateHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith(BEARER)) {
            throw new AuthException(ExceptionUtil.AUTH_HEADER_EXCEPTION);
        }
        return authHeader.substring(BEGIN_INDEX);
    }

}
