package com.blog.api.repository.blog;

import com.blog.api.domain.blog.Post;
import com.blog.api.domain.blog.PostStatus;
import com.blog.api.domain.blog.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByStatus(PostStatus status, Sort postAt);

    List<Post> findByTagAndStatus(Tag tag, PostStatus status, Sort postAt);
}
