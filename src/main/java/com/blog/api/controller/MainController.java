package com.blog.api.controller;

import com.blog.api.anotation.CurrentAccount;
import com.blog.api.domain.account.Account;
import com.blog.api.domain.blog.Tag;
import com.blog.api.repository.tag.TagRepository;
import com.blog.api.service.tag.TagService;
import com.blog.api.domain.tag.form.TagForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final TagService tagService;
    private final TagRepository tagRepository;
    private final ObjectMapper objectMapper;

    @GetMapping("/")
    public String home(@CurrentAccount Account account, Model model) throws JsonProcessingException {

        if (account != null) {
            model.addAttribute("account", account);
        }
        model.addAttribute("tagForm", new TagForm());
        List<Tag> allTags = tagRepository.findAll().stream()
                .collect(Collectors.toList());

        //model.addAttribute("tagList", objectMapper.writeValueAsString(allTags));
        model.addAttribute("tagList", allTags);
        return "main";
    }
}
