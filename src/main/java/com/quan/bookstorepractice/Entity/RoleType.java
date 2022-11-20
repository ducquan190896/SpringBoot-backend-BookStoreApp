package com.quan.bookstorepractice.Entity;

public enum RoleType {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    MANAGER("ROLE_MANAGER");

    private String name;
    RoleType(String name) {
        this.name = name;
    }

    public String getRoleType() {
        return this.name;
    }
}
