package com.blog.api.controller.account;

import com.blog.api.anotation.CurrentAccount;
import com.blog.api.domain.account.Account;
import com.blog.api.service.account.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
        
    //TODO 
    // 나중에 id/pw 인증부 추가하면 이관
    private final CustomOAuth2UserService userService;
    
    @GetMapping("/users/me")
    public ResponseEntity getCurrentAccount(@CurrentAccount Account account) {
        return ResponseEntity.ok(account);
    }

}
