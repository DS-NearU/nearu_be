package com.nearu.nearu.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Comment {
    private Integer commentNo;
    private Integer userNo;
    private Integer qaNo;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Comment() {
    }

    public Comment(Integer userNo, Integer qaNo, String content, LocalDateTime createdAt) {
        this.userNo = userNo;
        this.qaNo = qaNo;
        this.content = content;
        this.createdAt = createdAt;
    }
}
