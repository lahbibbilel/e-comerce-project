package com.example.ecomercebackend.Config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;

@Service
public class JwtService {

    //Encryption Key Generator
private static final String SECRET_KEY="71337436773979244226452948404D635166546A576E5A723475377821412543";
    public String extractUsername(String token) {
        return null;
    }
   //lire partie claims de token
    //jwt.io
    //JSON web tokens (JWTs) claims are pieces of information asserted about a subject. For example, an ID token
   // (which is always a JWT ) can contain a claim called name that asserts that the name of the user authenticating is "John Doe".
    private Claims extactAllClaims(String token)
    {
        //secret key
        //generer sacret key
     return Jwts.parserBuilder().
             setSigningKey(getSigninkey()).
             build().
             parseClaimsJwt(token).
             getBody();
    }

    private Key getSigninkey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
