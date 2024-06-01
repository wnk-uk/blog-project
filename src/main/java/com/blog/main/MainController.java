package com.blog.main;

import com.blog.account.CurrentAccount;
import com.blog.domain.Account;
import com.blog.domain.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(@CurrentAccount Account account, Model model) {

        if (account != null) {
            model.addAttribute(account);
            model.addAttribute("tag", Tag.builder().tagName("spec").build());
        }

        return "index";
    }
}
