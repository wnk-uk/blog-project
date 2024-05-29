package com.blog.domain;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserAccount extends User {

    private Account account;

    public UserAccount(Account account) {
        super(account.getName(), account.getEmail(), List.of(new SimpleGrantedAuthority(account.getRoleKey())));
        this.account = account;
    }
}
