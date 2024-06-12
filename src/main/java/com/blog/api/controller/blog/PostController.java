package com.blog.api.controller.blog;

import com.blog.api.anotation.CurrentAccount;
import com.blog.api.domain.account.Account;
import com.blog.api.domain.blog.Post;
import com.blog.api.domain.blog.Tag;
import com.blog.api.domain.blog.form.PostForm;
import com.blog.api.domain.blog.validator.PostValidator;
import com.blog.api.service.post.PostService;
import com.blog.api.service.tag.TagService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostValidator postValidator;
    private final PostService postService;
    private final TagService tagService;

    @InitBinder("tagForm")
    public void postBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(postValidator);
    }

    @PostMapping("/posts/add")
    @ResponseBody
    public ResponseEntity addPost(@CurrentAccount Account account, @Validated @RequestBody PostForm postForm, Errors errors) {

        if (errors.hasErrors()) {
            throw new RuntimeException(errors.getFieldError("error").getDefaultMessage());
        }

        postService.createPost(postForm);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tags/{id}/posts")
    public ResponseEntity getPostList(@PathVariable(name = "id") Long id) {
        System.out.println("id :" + id);
        Tag tag = tagService.getPostList(id);
        return ResponseEntity.ok(tag);
    }

}
