package com.expirtion_alert.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.access-token-expiration}")
    private Long expiration;
    @Value("${jwt.refresh-token-expiration}")
    private Long refreshExpiration;


    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateToken(String email){
      return   Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .claim("type","access")
                .signWith(getKey())
                .compact();
    }

    public String generateRefreshToken(String email){
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+refreshExpiration))
                .claim("type","refresh")
                .signWith(getKey())
                .compact();
    }

    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }

public boolean validateToken(String token){
        try{
            return !isTokenExpired(token);
        }catch (JwtException|IllegalArgumentException e){
            return false;
        }
}


    public boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }


    public Claims extractClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }




}
