package com.quan.bookstorepractice.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.bookstorepractice.Entity.Role;
import com.quan.bookstorepractice.Service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleControler {
    @Autowired
    RoleService roleService;

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity(roleService.getRoles(), HttpStatus.OK);
    }
    @GetMapping("/roleName/{rolename}")
    public ResponseEntity<Role> getRole(@PathVariable String rolename) {
        // return new ResponseEntity<>(roleService.getRole(rolename), HttpStatus.OK);
        return new ResponseEntity<>(roleService.getRole(rolename), HttpStatus.OK);
    }

    @DeleteMapping("/roleName/{rolename}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable String rolename) {
        roleService.deleteRole(rolename);
        return new ResponseEntity<HttpStatus>( HttpStatus.NO_CONTENT);
    }
}
