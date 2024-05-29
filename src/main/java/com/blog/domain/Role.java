package com.blog.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("GUEST", "게스트"),
    USER("USER", "유저"),
    ADMIN("ADMIN", "관리자");

    private final String key;
    private final String title;



}
