package com.company.constructionmanagementsystem.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtConverter {

    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final String ISSUER = "construction-management-service";
    private final int EXPIRATION_MINUTES = 15;
    private final int EXPIRATION_MILLIS = EXPIRATION_MINUTES * 60 * 1000;

    public String getTokenFromUser(User user) {
        String authorities = user.getAuthorities().stream()
                .map(i -> i.getAuthority())
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(user.getUsername())
                .claim("authorities", authorities)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(key)
                .compact();
    }

    public User getUserFromToken(String token) {

        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .requireIssuer(ISSUER)
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.substring(7));

            String username = jws.getBody().getSubject();
            String authStr = (String) jws.getBody().get("authorities");
            List<GrantedAuthority> authorities = Arrays.stream(authStr.split(","))
                    .map(i -> new SimpleGrantedAuthority(i))
                    .collect(Collectors.toList());

            return new User(username, username, authorities);

        } catch (JwtException e) {
            System.out.println(e);
        }

        return null;
    }
}
