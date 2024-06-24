package com.blog.api.repository.blog;

import com.blog.api.domain.blog.PostFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<PostFile, Long> {
}
