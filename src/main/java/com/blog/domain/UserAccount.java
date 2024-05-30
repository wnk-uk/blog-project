package com.blog.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

@Getter
public class UserAccount extends User {

    private Account account;
    public UserAccount(Account account) {
        super(account.getName(), "", List.of(new SimpleGrantedAuthority(account.getRoleKey())));
    }
}
