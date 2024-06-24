package com.blog.api.repository.blog;

import com.blog.api.domain.blog.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
