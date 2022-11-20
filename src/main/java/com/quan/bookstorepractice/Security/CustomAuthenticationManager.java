package com.quan.bookstorepractice.Security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.quan.bookstorepractice.Entity.Account;
import com.quan.bookstorepractice.Repository.AccountRepo;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {
    
    @Autowired 
    AccountRepo accountRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
       
        Optional<Account> entity = accountRepo.findByUsername(username);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the username with name " + username + " not found" );
        }
        Account account = entity.get();
        System.out.println(account.toString());
        if(!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), account.getPassword())) {
            throw new BadCredentialsException("password provided is wrong");
        } 
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole().getRoleType().getRoleType()));
        return new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword(), authorities);
    
    }
}
