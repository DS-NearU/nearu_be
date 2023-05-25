package com.nearu.nearu;
import java.util.*;
public class CommentService implements CommentRepository {

    public static Map<Integer, Comment> comMap = new HashMap<>();

    @Override
    public boolean save(Comment c) {
        for (Comment com : comMap.values()) {
            if (com.getCommentNo()==c.getCommentNo()) {
                comMap.put(c.getCommentNo(), c);
            }
        }
    }

    @Override
    public Comment fetch(Integer commentNo) {
        for (Comment com : comMap.values()) {
            if (com.getCommentNo() == commentNo) {
                return com;
            }
        }
        return null;
    }

    @Override
    public void update(Comment c) {
        comMap.put(c.getCommentNo(), c);
    }

    @Override
    public void delete(Integer commentNo) {
        comMap.remove(commentNo);
    }
}
