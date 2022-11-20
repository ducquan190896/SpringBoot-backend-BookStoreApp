package com.quan.bookstorepractice.Entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor

@Table(name = "account")
@Entity(name = "Account")

public class Account {
    @Id
    @SequenceGenerator(
        name = "user_sequence",
        sequenceName = "user_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    
    @NotBlank(message = "username cannot be blank")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

   
    @NotBlank(message = "password cannot be blank")
    @Column(name = "password", nullable = false)
    private String password;

  
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_role_id"))
    private Role role;

    public Account(String username, String password) {
        this.username = username;
        this.password = new BCryptPasswordEncoder().encode(password).toString();
    }
    public Account(String username, String password, Role role) {
        this.username = username;
        this.password = new BCryptPasswordEncoder().encode(password).toString();
        this.role = role;
    }
    
    
}
