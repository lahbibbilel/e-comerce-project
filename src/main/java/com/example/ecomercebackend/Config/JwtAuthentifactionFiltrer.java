package com.example.ecomercebackend.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public final class JwtAuthentifactionFiltrer extends OncePerRequestFilter {
private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        // passer token on the header
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        // si token in the header = null ou ne débuter pas with bearer do next
        if(authHeader==null || !authHeader.startsWith("Bearer"))
        {
            filterChain.doFilter(request,response);
            return;
        }
        //7 c'est le nombre de longeur du mot bearer
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
    }
}
