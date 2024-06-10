package com.blog.api.domain.account;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "attribute_id")
    private String attributeId;             // 간편로그인 계정의 고유 ID

    @Column(name = "registration_id")
    private String registrationId;          // 간편로그인 고유 계정 (ex, google, kakao)

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private String picture;

    private LocalDateTime joinAt;

    public String getRoleKey() {
        return this.role.getKey();
    }

    public Account update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }
}
