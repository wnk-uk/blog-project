package com.blog.api.domain.blog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostFile {


    @GeneratedValue
    @Id
    private Long id;

    private String originFileName;
    private String fileName;
    private String fileUrl;
    private Long fileSize;
    private LocalDateTime createAt;
    private String ext;
    private String contentType;


}
