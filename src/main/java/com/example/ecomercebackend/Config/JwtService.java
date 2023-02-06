package com.example.ecomercebackend.Config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

private static final String SECRET_KEY="";
    public String extractUsername(String token) {
        return null;
    }
    private Claims extactAllClaims(String token)
    {
        //secret key
        //generer sacret key
     return null;
             //Jwts.parserBuilder().setSigningKey(getSigninkey()).build().parseClaimsJwt(token).getBody();
    }
}
