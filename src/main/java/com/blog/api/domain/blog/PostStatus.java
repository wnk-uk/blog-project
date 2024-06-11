package com.blog.api.domain.blog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostStatus {

    TEMPSAVE("T", "임시저장"),
    POSTING("P", "포스팅"),
    DELETED("D", "삭제");

    private final String key;
    private final String title;

    // 특정 키 값으로 Enum을 가져오는 메서드
    public static PostStatus fromKey(String key) {
        for (PostStatus status : PostStatus.values()) {
            if (status.getKey().equals(key)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant for key: " + key);
    }

}
