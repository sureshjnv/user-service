package com.automation.app.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.automation.app.entity.User;

public class UserPriciple implements UserDetails {

    private final User user;

    public UserPriciple(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Assuming a single role; modify this if the user has multiple roles
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify based on your requirements
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify based on your requirements
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify based on your requirements
    }


    // Expose additional user details
    public String getEmail() {
        return user.getEmail();
    }

    public String getRole() {
        return user.getRole();
    }

    public Integer getId() {
        return user.getId();
    }

    public User getUser() {
        return user;
    }
}
