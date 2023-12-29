package com.codecool.solarwatch.model;

import com.codecool.solarwatch.model.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Set;

@Entity
public class User {
    @Id
    private Long id;
    private String userName;
    private String password;
    private Set<Role> roles;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set<Role> roles() {
        return roles;
    }

    public String username() {
        return userName;
    }

    public String password() {
        return password;
    }
}