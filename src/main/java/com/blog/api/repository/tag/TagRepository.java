package com.blog.api.repository.tag;

import com.blog.api.domain.blog.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByTagName(String tagName);
}
