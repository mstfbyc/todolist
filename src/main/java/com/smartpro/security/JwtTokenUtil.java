package com.smartpro.security;

import com.smartpro.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
    //1 gün
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS= 24*60*60*100;
    public static final String SIGNING_KEY = "+KbPeShVmYq3s6v9y$B&E)H@McQfTjWn";

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpireDateFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }

    public  <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private  Boolean isTokenExpired(String token){
        final Date expiration = getExpireDateFromToken(token);
        return expiration.before(new Date());
    }
    public String generateToken(Users user){
        return doGenerateToken(user.getUsername());
    }

    private String doGenerateToken(String subject){
        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("USER")));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("localhost")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS))
                .signWith(SignatureAlgorithm.HS256,SIGNING_KEY)
                .compact();
    }
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
