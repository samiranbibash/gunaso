/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.envisionnepal.gunasho.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class JwtService {

   
     
    private final  String JWT_SECRET ="9DAA5957160FD4279BA29B5E2DBB70E7737D1672FAF440D2688DC3C4A1294A2B";

    private final  long JWT_EXPIRATION_TIME_IN_MILLISECONDS = 5 * 60000 * 60000;

    public JwtService() {
    }
    
    public String generateToken(String userName){
        
         Map<String, Object> claims = new HashMap<>();
        return tokenCreator(claims, userName);
    }
     public String tokenCreator(Map<String, Object> claims, String userName){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_EXPIRATION_TIME_IN_MILLISECONDS* 1000 * 60 * 60 * 1000000*100000*10000))
                .signWith(getSignedKey(), SignatureAlgorithm.HS256).compact();
    }
     public String extractUsernameFromToken(String theToken){
        return extractClaim(theToken, Claims ::getSubject);
    }
    public Date extractExpirationTimeFromToken(String theToken) {
        return extractClaim(theToken, Claims :: getExpiration);
    }

    public Boolean validateToken(String theToken, UserDetails userDetails){
        final String userName = extractUsernameFromToken(theToken);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(theToken));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
    public boolean isTokenExpired(String theToken) {
        return extractExpirationTimeFromToken(theToken).before(new Date());
    }
    private Key getSignedKey(){
        byte[] keyByte = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyByte);
    }
    
}
