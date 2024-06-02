package com.blog.main;

import com.blog.account.CurrentAccount;
import com.blog.domain.Account;
import com.blog.domain.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(@CurrentAccount Account account, Model model) {

        if (account != null) {
            model.addAttribute(account);
            model.addAttribute("tagForm", Tag.builder().tagName("spec").build());
        }

        return "main";
    }
}
