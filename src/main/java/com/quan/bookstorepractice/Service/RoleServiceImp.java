package com.quan.bookstorepractice.Service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quan.bookstorepractice.Entity.Role;
import com.quan.bookstorepractice.Entity.RoleType;
import com.quan.bookstorepractice.Repository.RoleRepo;

import lombok.AllArgsConstructor;

@Service
 @AllArgsConstructor
public class RoleServiceImp implements RoleService {

    RoleRepo roleRepo;

    @Override
    public void addRole(Role role) {
       roleRepo.save(role);
        
    }

    @Transactional
    @Override
    public void deleteRole(String roleName) {
        Optional<Role> entity = roleRepo.findByRoleType(roleName);
        if(entity.isPresent()) {
            roleRepo.delete(entity.get());;
        } else {
            throw new EntityNotFoundException("the role with role name " + roleName + " not found");
        }
    

        
    }

    @Override
    public Role getRole(String roleName) {
        
        Optional<Role> entity = roleRepo.findByRoleType(roleName);
        if(entity.isPresent()) {
           
            Role role = entity.get();
            return role;
         
        }
     
        throw new EntityNotFoundException("the role with role name " + roleName + " not found");
    }

    @Override
    public List<Role> getRoles() {
        
        return roleRepo.findAll();
    }
    
   
   

    
}
