package com.blog.config;

import com.blog.api.service.account.CustomOAuth2UserService;
import com.blog.oauth2.*;
import com.blog.api.domain.account.Role;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${spring.front.url}")
    private String FRONT_URL;

    private static String LOGOUT_URL = "/oauth2/logout";

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final OAuth2FailureHandler oAuth2FailureHandler;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((auth) ->
                        auth.requestMatchers(
                                    AntPathRequestMatcher.antMatcher("/auth/**")
                                ).hasAnyAuthority(Role.ADMIN.getKey())
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/blog/**")
                                )
                                .hasAnyAuthority(Role.ADMIN.getKey())
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/h2-console/**")
                                ).permitAll()
                                .anyRequest().permitAll())
                .logout(
                        (logout) ->
                                logout.logoutUrl("/logout").logoutSuccessUrl(FRONT_URL + LOGOUT_URL)
                )
                .csrf((csrf) -> csrf.disable())
                .oauth2Login((oauth) -> oauth
//                    .authorizationEndpoint(config ->
//                        config.authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository)
//                    )
                    .successHandler(oAuth2SuccessHandler)
                    .failureHandler(oAuth2FailureHandler)
                    .userInfoEndpoint(
                            (user) -> user.userService(customOAuth2UserService)
                    )
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .headers((headerConfig) -> headerConfig.frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::sameOrigin
                ))
                .cors((cors) -> cors.configurationSource( //TODO
                        new CorsConfigurationSource() {
                            @Override
                            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                                CorsConfiguration config = new CorsConfiguration();
                                config.setAllowedOrigins(List.of(FRONT_URL));
                                config.setAllowedMethods(List.of("*"));
                                config.setAllowCredentials(true);
                                config.setAllowedHeaders(List.of("*"));
                                config.setMaxAge(3600L);
                                return config;
                            }
                        }

                ));
        ;
        return http.build();
    }

    //static resource들은 별도로 인증을 거치지 않음
    //TODO
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // antMatchers 부분도 deprecated 되어 requestMatchers로 대체
        return (web) -> web.ignoring()
                .requestMatchers("/node_modules/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

}
