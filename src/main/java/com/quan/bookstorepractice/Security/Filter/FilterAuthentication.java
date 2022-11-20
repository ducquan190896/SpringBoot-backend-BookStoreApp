package com.quan.bookstorepractice.Security.Filter;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.bookstorepractice.Entity.Account;
import com.quan.bookstorepractice.Security.CustomAuthenticationManager;
import com.quan.bookstorepractice.Security.SecurityConstant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FilterAuthentication extends UsernamePasswordAuthenticationFilter{

    CustomAuthenticationManager customAuthenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {   
            Account account = new ObjectMapper().readValue(request.getInputStream(), Account.class);
            // System.out.println(account.getUsername() + " : "+ account.getPassword());
            Authentication authentication1 = new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword());
            Authentication authentication = customAuthenticationManager.authenticate(authentication1);
            // System.out.println(authentication.getName() + " _ " + authentication1.getCredentials());
             return authentication;
           
        } catch (IOException ex) { 
            throw new RuntimeException();
        }
    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
       String username = authResult.getName();
      
       List<String> list = authResult.getAuthorities().stream().map(aut -> aut.getAuthority()).collect(Collectors.toList());
      String token = JWT
      .create()
      .withSubject(username)
      .withClaim("authorities", list)
      .withExpiresAt(new Date(System.currentTimeMillis()  + SecurityConstant.timeToke))
      .sign(Algorithm.HMAC512(SecurityConstant.private_key));

      response.setHeader("Authorization", SecurityConstant.authorizationi + token);
    }
}
