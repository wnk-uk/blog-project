package com.blog.oauth2;

import com.blog.domain.Account;
import com.blog.domain.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            CustomUserDetails customUserDetails =  jwtTokenProvider.getUserDetails(token);
            Account account = customUserDetails.getAccount();

            OAuth2AuthenticationToken oauthToken = new OAuth2AuthenticationToken (
                    customUserDetails,
                    List.of(new SimpleGrantedAuthority(account.getRoleKey())),
                    account.getRegistrationId()
            );
            SecurityContextHolder.getContext().setAuthentication(oauthToken);
        }

        chain.doFilter(request, response);
    }
}
