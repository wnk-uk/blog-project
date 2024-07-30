package com.blog.api.service.blog;

import com.blog.api.domain.blog.Post;
import com.blog.api.domain.blog.PostStatus;
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

    public void createTag(String tagName) {
        Tag tag = tagRepository.findByTagName(tagName);

        if (tag == null) {
            tagRepository.save(Tag.builder().tagName(tagName).build());
        }
    }



}
