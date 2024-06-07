package com.blog.api.service.tag;

import com.blog.api.domain.blog.Tag;
import com.blog.api.repository.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
