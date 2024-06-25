package com.blog.api.domain.blog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
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

    @Transient
    private byte[] binary;


    public void imageLoad(String url) throws IOException {
        binary = FileCopyUtils.copyToByteArray(new File(url));
    }
}
