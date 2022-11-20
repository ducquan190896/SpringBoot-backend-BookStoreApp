package com.quan.bookstorepractice.Entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Table(name = "role")
@Entity(name = "Role")
@Getter
@Setter
 @NoArgsConstructor
@RequiredArgsConstructor
public class Role {

    @Id
    @SequenceGenerator(
        name = "role_sequence",
        sequenceName = "role_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "role_sequence"
    )
    @Column(name = "id", updatable = false)
    private long id;

    @NonNull
    @NotNull(message = "role type cannot be blank")
    @Enumerated(EnumType.STRING)
    @Column(name = "roletype", nullable = false)
    private RoleType roleType;

    @JsonIgnore
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Account> accounts = new ArrayList<>();

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "role type is " + this.roleType.getRoleType();
    }

}
