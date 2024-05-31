package com.blog.tag;

import com.blog.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TagValidator implements Validator {

    private final TagRepository tagRepository;


    @Override
    public boolean supports(Class<?> clazz) {
        return TagForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TagForm tagForm = (TagForm) o;
        Tag tag = tagRepository.findByTagName(tagForm.getTagName());

        if (tag != null) {
            errors.rejectValue("tagName", "wrong.value", "중복된 태그명이 사용되었습니다.");
        }
    }
}
