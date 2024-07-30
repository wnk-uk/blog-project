package com.blog.api.domain.blog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore // Post 엔티티의 tag 필드를 무시
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Column(length = 100)
    private String description;

    @Basic(fetch = FetchType.EAGER)
    private String thumbnail;

    @Lob @Basic(fetch = FetchType.LAZY)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostStatus status;

    private LocalDateTime createAt;

    private LocalDateTime postAt;

    public String getPostStatus() {
        return this.status.getKey();
    }

    public void posting() {
        this.status = PostStatus.POSTING;
    }

}

