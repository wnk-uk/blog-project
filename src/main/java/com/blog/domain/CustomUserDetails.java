package com.blog.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class CustomUserDetails implements OAuth2User, UserDetails {

    private Account account;
    private Map<String, Object> attribute;
    private Collection<? extends GrantedAuthority> authorities;


    public CustomUserDetails(Account account, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attribute) {
        this.account = account;
        this.authorities = authorities;
        this.attribute = attribute;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attribute;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return account.getEmail();
    }

    @Override
    public String getName() {
        return account.getName();
    }
}
