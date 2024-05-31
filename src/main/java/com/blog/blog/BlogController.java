package com.blog.blog;

import com.blog.account.CurrentAccount;
import com.blog.domain.Account;
import com.blog.tag.TagForm;
import com.blog.tag.TagService;
import com.blog.tag.TagValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    @ResponseBody
    public ResponseEntity addTag(@CurrentAccount Account account, @RequestBody TagForm tagForm) {
        tagService.createTag(tagForm.getTagName());
        return ResponseEntity.ok().build();
    }


}
