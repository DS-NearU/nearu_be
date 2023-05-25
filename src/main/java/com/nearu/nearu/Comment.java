package com.nearu.nearu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
    private Integer commentNo;
    private Integer userNo;
    private Integer qaNo;
    private String content;
    private String createdAt;

    public Comment() {
    }

    public Comment(Integer userNo, Integer qaNo, String content, String createdAt) {
        this.userNo = userNo;
        this.qaNo = qaNo;
        this.content = content;
        this.createdAt = createdAt;
    }
}
