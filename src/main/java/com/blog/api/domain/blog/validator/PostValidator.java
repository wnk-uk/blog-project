package com.blog.api.domain.blog.validator;

import com.blog.api.domain.blog.form.PostForm;
import com.blog.api.domain.blog.form.TagForm;
import com.blog.api.repository.blog.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class PostValidator implements Validator  {

    private final PostRepository postRepository;


    @Override
    public boolean supports(Class<?> clazz) {
        return TagForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PostForm postForm = (PostForm) o;
        //Tag tag = postRepository.findByTagName(tagForm.getTagName());

        if (postForm.getTitle() != null) {
            errors.rejectValue("error", "wrong.value", "제목을 입력하세요.");
        }
    }
}
