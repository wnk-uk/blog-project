package com.blog.api.service.tag;

import com.blog.api.domain.blog.Post;
import com.blog.api.domain.blog.Tag;
import com.blog.api.repository.post.PostRepository;
import com.blog.api.repository.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService {

    private final TagRepository tagRepository;
    private final PostRepository postRepository;

    public void createTag(String tagName) {
        Tag tag = tagRepository.findByTagName(tagName);

        if (tag == null) {
            tagRepository.save(Tag.builder().tagName(tagName).build());
        }
    }


    public Tag getPostList(Long id) {
        return tagRepository.findTagWithPostsById(id).orElseThrow(() -> new NoSuchElementException("Tag not found"));
    }
}
