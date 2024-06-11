package com.blog.api.domain.blog.form;

import lombok.Data;

@Data
public class PostForm {

    private String title;
    private String content;
    private String postStatus;
    private Long tag;

}
