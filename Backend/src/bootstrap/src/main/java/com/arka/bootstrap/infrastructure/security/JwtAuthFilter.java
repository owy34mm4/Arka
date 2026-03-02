package com.arka.bootstrap.infrastructure.security;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter{

    private final JwtAdapter jwtAdapter;  
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,  
                                    HttpServletResponse response,  
                                    FilterChain filterChain)  
            throws ServletException, IOException, java.io.IOException {  
  
        String authHeader = request.getHeader("Authorization");  
  
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {  
            filterChain.doFilter(request, response);  
            return;  
        }  
  
        String token = authHeader.substring(7);  
  
        if (jwtAdapter.isTokenValid(token)) {  
            String username = jwtAdapter.extractUsername(token);  
            String role = jwtAdapter.extractRole(token);  
            Long userId = jwtAdapter.extractUserId(token);
  
            UsernamePasswordAuthenticationToken auth =  
                new UsernamePasswordAuthenticationToken(  
                    username,  
                    null,  
                    List.of(new SimpleGrantedAuthority(role))  
                    // List.of(new SimpleGrantedAuthority("ROLE_" + role))  
                );  
            auth.setDetails(new AuthenticatedUserDetails(username,role,userId));  
            // auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));  
            SecurityContextHolder.getContext().setAuthentication(auth);  
        }  
  
        filterChain.doFilter(request, response);  
    }
    
}
