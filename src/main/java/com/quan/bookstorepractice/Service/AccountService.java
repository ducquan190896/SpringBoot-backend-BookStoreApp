package com.quan.bookstorepractice.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quan.bookstorepractice.Entity.Account;


public interface AccountService {
    
    void saveAccount(Account account);
    List<Account> getAccounts();
    Account getAccount(Long id);
    Account updateAccount(Long id, String password);
    Account updateRoleToAccount(Long id, String rolename);
    void deleteAccount(Long id);
}
