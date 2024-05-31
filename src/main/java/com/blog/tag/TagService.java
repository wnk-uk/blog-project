package com.blog.tag;

import com.blog.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
