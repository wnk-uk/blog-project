package com.blog.api.controller.blog;

import com.blog.api.anotation.CurrentAccount;
import com.blog.api.domain.account.Account;
import com.blog.api.domain.tag.form.TagForm;
import com.blog.api.service.tag.TagService;
import com.blog.api.domain.tag.validator.TagValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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
