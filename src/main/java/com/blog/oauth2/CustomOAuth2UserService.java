package com.blog.oauth2;

import com.blog.account.AccountRepository;
import com.blog.config.AppProperties;
import com.blog.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final AccountRepository accountRepository;
    private final AppProperties appProperties;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Account account = saveOrUpdate(attributes);
        updateRole(account);
        System.out.println(userRequest.getAccessToken().getTokenValue());

        // TODO : 핸들러로 빼줄 것
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(account)
                ,userRequest.getAccessToken().getTokenValue()
                ,List.of(new SimpleGrantedAuthority(account.getRoleKey()))
        );

        SecurityContextHolder.getContext().setAuthentication(token);

        return new CustomUserDetails(account, List.of(new SimpleGrantedAuthority(account.getRoleKey())), attributes.getAttributes());
    }

    private void updateRole(Account account) {
        if (appProperties.getAdminEmail().intern() == account.getEmail().intern()) account.setRole(Role.ADMIN);
    }

    private Account saveOrUpdate(OAuthAttributes attributes) {
        Account account = accountRepository.findByEmail(attributes.getEmail())
                .map((entity) -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return accountRepository.save(account);
    }
}
