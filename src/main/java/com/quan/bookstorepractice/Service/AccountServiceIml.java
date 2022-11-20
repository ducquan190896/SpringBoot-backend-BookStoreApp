package com.quan.bookstorepractice.Service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.quan.bookstorepractice.Entity.Account;
import com.quan.bookstorepractice.Entity.Role;
import com.quan.bookstorepractice.Entity.RoleType;
import com.quan.bookstorepractice.Exception.EntityNotFoundException;
import com.quan.bookstorepractice.Repository.AccountRepo;
import com.quan.bookstorepractice.Repository.RoleRepo;

@Service
public class AccountServiceIml implements AccountService{

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    RoleRepo roleRepo;
    

    @Override
    public void deleteAccount(Long id) {
        Optional<Account> entity = accountRepo.findById(id);
        Account account = isCheck(entity, id);
        accountRepo.delete(account);
        
    }

    @Override
    public Account getAccount(Long id) {
       Optional<Account> entity = accountRepo.findById(id);
        Account account = isCheck(entity, id);
        return account;
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepo.findAll();
    }

    @Override
    public void saveAccount(Account account) {
        Optional<Account> entity = accountRepo.findByUsername(account.getUsername());
        if(entity.isPresent()) {
            throw new EntityNotFoundException("the username with " + account.getUsername() + " already exists");
        } else {
            Role role = roleRepo.findByRoleType(RoleType.USER.name()).get();
            account.setRole(role);
            accountRepo.save(account);
        }

        
    }

    @Override
    public Account updateAccount(Long id, String password) {
        Optional<Account> entity = accountRepo.findById(id);
        Account account = isCheck(entity, id);
        account.setPassword(new BCryptPasswordEncoder().encode(password));
       return accountRepo.save(account);
    }

    @Override
    public Account updateRoleToAccount(Long id, String rolename) {
        Optional<Account> entity = accountRepo.findById(id);
        Account account = isCheck(entity, id);
      Optional<Role> role = roleRepo.findByRoleType(rolename);
      if(role.isPresent()) {
        account.setRole(role.get());
        return accountRepo.save(account);
      } else {
        throw new EntityNotFoundException("the role  with role name " + rolename + " is not found");
      }
     
       
    }
    private Account isCheck(Optional<Account> entity, Long id) {
        if(!entity.isPresent()) {
            throw new EntityNotFoundException("the account with id " + id + " is already exist");
        }
        return entity.get();
    }
} 
