package com.blog.api.domain.blog;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;



@NamedEntityGraph(name="Tag.withPosts", attributeNodes  = {
        @NamedAttributeNode("posts")
})
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Tag {

    @GeneratedValue
    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String tagName;

    @OneToMany(mappedBy = "tag")
    @OrderBy("postAt desc")
    private List<Post> posts;


    public void addPost(Post post) {
        posts.add(post);
        post.setTag(this);
    }
}
