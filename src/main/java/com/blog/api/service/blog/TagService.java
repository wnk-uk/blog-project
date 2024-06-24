package com.blog.api.service.blog;

import com.blog.api.domain.blog.Post;
import com.blog.api.domain.blog.Tag;
import com.blog.api.repository.blog.PostRepository;
import com.blog.api.repository.blog.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    public List<Post> getPostAll() {
        return postRepository.findAll(Sort.by(Sort.Order.desc("postAt"))).stream().collect(Collectors.toList());
    }
}
