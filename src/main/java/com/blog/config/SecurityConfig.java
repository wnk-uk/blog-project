package com.blog.config;

import com.blog.account.CustomOAuth2UserService;
import com.blog.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((auth) ->
                        auth.requestMatchers(
                                    AntPathRequestMatcher.antMatcher("/auth/**")
                                ).hasRole(Role.ADMIN.getKey())
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/h2-console/**")
                                ).permitAll()
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/blog/**")
                                ).hasRole(Role.USER.getKey())
                                .anyRequest().permitAll())
                .logout((logout) -> logout.logoutSuccessUrl("/"))
                .csrf((csrf) -> csrf.disable())
                .oauth2Login((oauth) -> oauth.userInfoEndpoint((user) -> user.userService(customOAuth2UserService)))
                .headers((headerConfig) -> headerConfig.frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::sameOrigin
                ))

        ;

        return http.build();
    }
}
