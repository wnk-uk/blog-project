package com.blog.blog;

import com.blog.account.CurrentAccount;
import com.blog.domain.Account;
import com.blog.tag.form.TagForm;
import com.blog.tag.TagService;
import com.blog.tag.validator.TagValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    private final TagValidator tagValidator;
    private final TagService tagService;

    @InitBinder("tagForm")
    public void tagBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(tagValidator);
    }

    @PostMapping("/tags/add")
    public String addTag(@CurrentAccount Account account, Model model, @Validated TagForm tagForm, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute(account);
            return "main";
        }

        tagService.createTag(tagForm.getTagName());
        return "redirect:/";
    }


}
