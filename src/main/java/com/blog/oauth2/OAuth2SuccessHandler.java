package com.blog.oauth2;

import com.blog.domain.Account;
import com.blog.domain.CustomUserDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    //TODO 나중에 설정값으로 변경
    static final String REDIRECT_URL = "http://localhost:8080/oauth2/redirect";
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Account account = customUserDetails.getAccount();

        String token = jwtTokenProvider.generateToken(account);

        String uri = UriComponentsBuilder.fromUriString(REDIRECT_URL)
                        .queryParam("token", token)
                .build().toUriString();

        response.sendRedirect(uri);
    }
}
