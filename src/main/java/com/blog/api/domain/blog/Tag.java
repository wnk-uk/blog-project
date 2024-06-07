package com.blog.api.domain.blog;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

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

}
