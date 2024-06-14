package com.blog.api.service.post;

import com.blog.api.domain.blog.Post;
import com.blog.api.domain.blog.PostStatus;
import com.blog.api.domain.blog.Tag;
import com.blog.api.domain.blog.form.PostForm;
import com.blog.api.repository.post.PostRepository;
import com.blog.api.repository.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final TagRepository tagRepository;
    private final PostRepository postRepository;


    public void createPost(PostForm postForm) {
        Tag tag = tagRepository.findById(postForm.getTag()).orElseThrow(RuntimeException::new);
        
        //PostForm에 Mapper로 이관필요
        Post post = postRepository.save(Post.builder()
                .tag(tag)
                .title(postForm.getTitle())
                .postAt(LocalDateTime.now())
                .createAt(LocalDateTime.now())
                .status(PostStatus.fromKey(postForm.getPostStatus()))
                .content(postForm.getContent()).build());

        tag.addPost(post);
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("post not found"));
    }

}
