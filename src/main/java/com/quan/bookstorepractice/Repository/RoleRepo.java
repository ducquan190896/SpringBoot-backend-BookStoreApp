package com.quan.bookstorepractice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quan.bookstorepractice.Entity.Role;
import com.quan.bookstorepractice.Entity.RoleType;

@Repository
public interface RoleRepo  extends JpaRepository<Role, Long>{

    // @Transactional
    // @Modifying
    @Query(
        value = "DELETE from role where roletype = ?1",
        nativeQuery = true
    )
    void deleteByName(String roleName);

    @Query(
        value = "select * from role where roletype = ?1",
        nativeQuery = true
    )
    Optional<Role> findByRoleType(String name);
}
