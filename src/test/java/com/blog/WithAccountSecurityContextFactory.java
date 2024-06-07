package com.blog;

import com.blog.api.domain.account.Account;
import com.blog.api.domain.account.CustomUserDetails;
import com.blog.api.domain.account.OAuthAttributes;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WithAccountSecurityContextFactory implements WithSecurityContextFactory<WithAccount> {

    @Override
    public SecurityContext createSecurityContext(WithAccount account) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("name", account.name());
        attributes.put("email", account.email());
        attributes.put("picture", account.picture());

        // TODO
        OAuthAttributes oAuthAttributes = OAuthAttributes.of("google", "sub", attributes);
        Account AccountEntity = oAuthAttributes.toEntity();


        CustomUserDetails customUserDetails = new CustomUserDetails(
                AccountEntity,
                List.of(new SimpleGrantedAuthority(AccountEntity.getRoleKey())),
                attributes
        );

        OAuth2AuthenticationToken token = new OAuth2AuthenticationToken(
                customUserDetails,
                List.of(new SimpleGrantedAuthority(AccountEntity.getRoleKey())),
                AccountEntity.getRegistrationId()
        );

        context.setAuthentication(token);
        return context;
    }
}
