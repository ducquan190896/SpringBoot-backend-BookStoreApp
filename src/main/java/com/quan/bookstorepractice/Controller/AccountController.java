package com.quan.bookstorepractice.Controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quan.bookstorepractice.Entity.Account;
import com.quan.bookstorepractice.Service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    AccountService accountService;

  
    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<HttpStatus> Register(@Valid @RequestBody Account account) {
        accountService.saveAccount(account);
        return new ResponseEntity<HttpStatus>( HttpStatus.CREATED);
    }

    @GetMapping("/allAccounts")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Account>> getAccounts() {
        return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Account> getAccount( @PathVariable Long id) {
        return new ResponseEntity<>(accountService.getAccount( id), HttpStatus.OK);
    }
    @DeleteMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> deleteAccount( @PathVariable Long id) {
        accountService.deleteAccount(id);; 
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/id/{id}/updatePassword")
    public ResponseEntity<Account> updateAccount(@RequestBody String password, @PathVariable Long id) {
       
        return new ResponseEntity<Account>( accountService.updateAccount(id, password), HttpStatus.ACCEPTED);
    }

    @PutMapping("/id/{id}/role/{rolename}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Account> updateRoleToAccount(@PathVariable Long id, @PathVariable String rolename) {
        return new ResponseEntity<>(accountService.updateRoleToAccount(id, rolename), HttpStatus.ACCEPTED);
    }
}
