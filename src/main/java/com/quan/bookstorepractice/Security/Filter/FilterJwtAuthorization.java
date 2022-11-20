package com.quan.bookstorepractice.Security.Filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.quan.bookstorepractice.Security.SecurityConstant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FilterJwtAuthorization extends OncePerRequestFilter  {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if(authorization == null || !authorization.startsWith(SecurityConstant.authorizationi)) {
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println(authorization);
        String token = authorization.replace(SecurityConstant.authorizationi, "");
        System.out.println(token);
       try {
        DecodedJWT decodedJwt = JWT.require(Algorithm.HMAC512(SecurityConstant.private_key)).build().verify(token);
        String username = decodedJwt.getSubject();
         List<String> list =  decodedJwt.getClaim("authorities").asList(String.class);
         list.stream().forEach(clai -> System.out.println(clai.toString()));
         List<SimpleGrantedAuthority> authorities = list.stream().map(clai -> new SimpleGrantedAuthority(clai.toString())).collect(Collectors.toList());

         Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

         SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
       } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
       } 

    }
}
