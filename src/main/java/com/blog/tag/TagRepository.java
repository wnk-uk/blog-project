package com.blog.tag;

import com.blog.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByTagName(String tagName);
}
