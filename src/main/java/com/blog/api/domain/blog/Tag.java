package com.blog.api.domain.blog;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {

    @GeneratedValue
    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String tagName;

    @OneToMany
    private List<Post> posts;


}
