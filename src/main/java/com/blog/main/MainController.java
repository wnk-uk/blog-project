package com.blog.main;

import com.blog.account.CurrentAccount;
import com.blog.domain.Account;
import com.blog.domain.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(@CurrentAccount Account account, Model model, Authentication authentication) {
        if (account != null) {
            model.addAttribute(account);
        }
        return "index";
    }

}
