package com.blog.api.repository.blog;

import com.blog.api.domain.blog.Tag;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByTagName(String tagName);

    @EntityGraph(value="Tag.withPosts", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Tag> findTagWithPostsById(Long id);



}
