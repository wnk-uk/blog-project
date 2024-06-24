package com.blog.api.controller.blog;

import com.blog.api.anotation.CurrentAccount;
import com.blog.api.domain.account.Account;
import com.blog.api.domain.blog.Post;
import com.blog.api.domain.blog.PostFile;
import com.blog.api.domain.blog.Tag;
import com.blog.api.domain.blog.form.PostForm;
import com.blog.api.domain.blog.validator.PostValidator;
import com.blog.api.service.blog.PostService;
import com.blog.api.service.blog.TagService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/posts")
    public ResponseEntity getPostAll() {
        List<Post> posts = tagService.getPostAll();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/tags/{id}/posts")
    public ResponseEntity getPostList(@PathVariable(name = "id") Long id) {
        Tag tag = tagService.getPostList(id);
        return ResponseEntity.ok(tag);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity getPost(@PathVariable(name = "id") Long id) {
        Post post = postService.getPost(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping("/posts/inline/add")
    public ResponseEntity uploadInline(@RequestParam(value="image") MultipartFile files) throws FileUploadException {
        PostFile postFile = postService.uploadInline(files);
        return ResponseEntity.ok(postFile);
    }

    @GetMapping("/posts/inlines/{id}")
    public ResponseEntity loadInline(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().build();
    }

}
