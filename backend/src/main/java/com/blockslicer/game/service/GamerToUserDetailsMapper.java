package com.blockslicer.game.service;

import com.blockslicer.game.entity1.GamerCredentialsReadEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class GamerToUserDetailsMapper implements UserDetails {

    private String username;
    private String password;
    private String role;

    public GamerToUserDetailsMapper(GamerCredentialsReadEntity gamerCredentialsReadEntity) {
        this.username = gamerCredentialsReadEntity.getUserId();
        this.password = gamerCredentialsReadEntity.getPassword();
        this.role = gamerCredentialsReadEntity.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
