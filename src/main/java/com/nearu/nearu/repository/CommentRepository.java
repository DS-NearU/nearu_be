package com.nearu.nearu.repository;

import com.nearu.nearu.entity.Comment;

import java.util.ArrayList;

public interface CommentRepository
{
    void save (Comment c);

    Comment fetch (Integer commentNo);
    ArrayList<Comment> fetchAllByQa(Integer qaNo);
    void update (Comment c);
    void delete (Integer commentNo);
    void deleteAll(Integer qaNo);
}
