package com.example.demo.Jwt;

import com.example.demo.Service.UserDetailsImp;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${insaf.app.jwtSecret}")
    private String jwtSecret;
    @Value("${insaf.app.jwtRefreshExpirationMs}")
    private int jwtExpirationMs;

    public String generateToken(UserDetailsImp userPrincipal) {
        return generateTokenFromUsername(userPrincipal.getUsername());
    }
    public String generateTokenFromUsername(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public String generateTokenFromusername(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }
    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String authtoken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authtoken);
            return true;
        }catch (SignatureException e){
            logger.error("invalid jwt signature:{}",e.getMessage());
        }catch (MalformedJwtException e){
            logger.error("invalid jwt token:{}",e.getMessage());
        }catch (ExpiredJwtException e){
            logger.error("jwt token is expired :{}",e.getMessage());
        }catch (UnsupportedJwtException e){
            logger.error("jwt token is unsupported:{}",e.getMessage());
        }catch (IllegalArgumentException e){
            logger.error("jwt claims string is empty:{}",e.getMessage());
        }
        return false;
    }

}
