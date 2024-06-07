package com.blog.oauth2;

import com.blog.api.domain.account.Account;
import com.blog.api.domain.account.CustomUserDetails;
import com.blog.api.service.account.CustomOAuth2UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final long tokenValidMillisecond = 1000L * 60 * 60;

    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    private final CustomOAuth2UserService customOAuth2UserService;

    public String generateToken(Account account) {
        Claims claims = Jwts.claims().setSubject(account.getName());
        Date now = new Date();

        String token =  Jwts.builder()
                .setClaims(claims)
                .setIssuer(account.getEmail())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
                .signWith(key)
                .compact();

        return token;
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUserEmail(String token) {
        String info = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody().getIssuer();
        return info;
    }

    public CustomUserDetails getUserDetails(String token) {
        String email = this.getUserEmail(token);
        return customOAuth2UserService.loadUserEmail(email);
    }

    public String resolveToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try{
            Claims claims = extractAllClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch(Exception e){
            return false;
        }
    }

}
