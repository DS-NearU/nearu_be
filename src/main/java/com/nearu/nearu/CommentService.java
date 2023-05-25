package com.nearu.nearu;
import java.util.*;
public class CommentService implements CommentRepository {

    public static Map<Integer, Comment> comMap = new HashMap<>();

    public static int COM_NUM=1;
    @Override
    public void save(Comment c) {
        c.setCommentNo(COM_NUM);
        comMap.put(COM_NUM, c);
        COM_NUM++;
    }

    @Override
    public Comment fetch (Integer commentNo) {
        return comMap.get(commentNo);
    }

    @Override
    public ArrayList<Comment> fetchAllByQa(Integer qaNo) {
        ArrayList<Comment> allComments = new ArrayList<Comment>();
        for (Comment com : comMap.values()) {
            if (com.getQaNo() == qaNo) {
                allComments.add(com);
            }
        }
        return allComments;
    }

    @Override
    public void update(Comment c) {
        comMap.put(c.getCommentNo(), c);
    }

    @Override
    public void delete(Integer commentNo) {
        comMap.remove(commentNo);
    }

    @Override
    public void deleteAll(Integer qaNo){
        Set<Integer> keySet = comMap.keySet();
        for(Integer key : keySet)
        {
            Comment comment = comMap.get(key);
            if(comment.getQaNo()==qaNo){
                comMap.remove(key);
            }
        }
    }
}
