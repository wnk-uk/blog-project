package com.blog.api.domain.account;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String registrationId;
    private String name;
    private String email;
    private String picture;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(registrationId, userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name(String.valueOf(attributes.get("name")))
                .email(String.valueOf(attributes.get("email")))
                .picture(String.valueOf(attributes.get("picture")))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .registrationId(registrationId)
                .build();
    }

    public Account toEntity() {
        return Account.builder()
                .name(this.name)
                .email(this.email)
                .picture(this.picture)
                .role(Role.USER)
                .attributeId(this.nameAttributeKey)
                .registrationId(this.registrationId)
                .joinAt(LocalDateTime.now())
                .build();
    }
}
