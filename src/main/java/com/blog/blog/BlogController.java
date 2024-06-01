package com.blog.blog;

import com.blog.account.CurrentAccount;
import com.blog.domain.Account;
import com.blog.tag.TagForm;
import com.blog.tag.TagService;
import com.blog.tag.TagValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @InitBinder
    public void tagBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(tagValidator);
    }

    @PostMapping("/tags/add")
    public String addTag(@CurrentAccount Account account, @Validated TagForm tagForm, Errors errors) {
        if (errors.hasErrors()) {
            return "index";
        }

        tagService.createTag(tagForm.getTagName());
        return "redirect:/";
    }


}
