package com.quan.bookstorepractice.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quan.bookstorepractice.Entity.Role;
import com.quan.bookstorepractice.Entity.RoleType;


public interface RoleService  {
    List<Role> getRoles();
    Role getRole(String roleName);
    void addRole(Role role);
    @Transactional
    void deleteRole(String roleName);
}