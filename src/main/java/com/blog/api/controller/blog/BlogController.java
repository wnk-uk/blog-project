package com.blog.api.controller.blog;

import com.blog.api.anotation.CurrentAccount;
import com.blog.api.domain.account.Account;
import com.blog.api.domain.blog.Tag;
import com.blog.api.domain.blog.form.TagForm;
import com.blog.api.repository.blog.TagRepository;
import com.blog.api.service.blog.TagService;
import com.blog.api.domain.blog.validator.TagValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    private final ObjectMapper objectMapper;

    @InitBinder("tagForm")
    public void tagBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(tagValidator);
    }

    @PostMapping("/tags/add")
    @ResponseBody
    public ResponseEntity addTag(@CurrentAccount Account account, @Validated @RequestBody TagForm tagForm, Errors errors) {
        if (errors.hasErrors()) {
            throw new RuntimeException("동일한 명칭의 태그가 존재합니다.");
        }

        tagService.createTag(tagForm.getTagName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tags")
    @Transactional
    public ResponseEntity getTagList() throws JsonProcessingException {

        List<Tag> allTags = tagRepository.findAll().stream()
                .collect(Collectors.toList());

        return ResponseEntity.ok(objectMapper.writeValueAsString(allTags));
    }


}
