package com.blog.api.controller.blog;

import com.blog.api.anotation.CurrentAccount;
import com.blog.api.domain.account.Account;
import com.blog.api.domain.blog.Tag;
import com.blog.api.domain.tag.form.TagForm;
import com.blog.api.repository.tag.TagRepository;
import com.blog.api.service.tag.TagService;
import com.blog.api.domain.tag.validator.TagValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class BlogController {

    private final TagValidator tagValidator;
    private final TagRepository tagRepository;
    private final TagService tagService;

    @InitBinder("tagForm")
    public void tagBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(tagValidator);
    }

    @PostMapping("/tags/add")
    @ResponseBody
    public ResponseEntity addTag(@CurrentAccount Account account, @RequestBody TagForm tagForm) {
        tagService.createTag(tagForm.getTagName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tags")
    public ResponseEntity getTagList() {
        List<Tag> allTags = tagRepository.findAll().stream()
                .collect(Collectors.toList());
        return ResponseEntity.ok(allTags);
    }


}
