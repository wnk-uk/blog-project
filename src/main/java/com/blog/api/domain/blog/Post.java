package com.blog.api.domain.blog;

import jakarta.persistence.*;
import lombok.*;

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

    private String title;

    @ManyToOne
    private Tag tag;

    @Column(length = 1000)
    private String description;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String thumbnail;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String content;


}

