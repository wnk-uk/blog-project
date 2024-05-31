package com.blog.config;

import com.blog.oauth2.CustomOAuth2UserService;
import com.blog.domain.Role;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

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
                                )
                                //.hasRole(Role.ADMIN.getKey())
                                .permitAll()
                                .anyRequest().permitAll())
                .logout((logout) ->  logout.logoutSuccessUrl("/"))
                .csrf((csrf) -> csrf.disable())
                .oauth2Login((oauth) -> oauth.userInfoEndpoint((user) -> user.userService(customOAuth2UserService)))
                .headers((headerConfig) -> headerConfig.frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::sameOrigin
                ))
                .cors((cors) -> cors.configurationSource( //TODO
                        new CorsConfigurationSource() {
                            @Override
                            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                                CorsConfiguration config = new CorsConfiguration();
                                config.setAllowedOrigins(List.of("http://localhost:8080"));
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
