package com.nearu.nearu;

public interface CommentRepository
{
    boolean save (Comment c);
    Comment fetch (Integer commentNo);
    void update (Comment c);
    void delete (Integer commentNo);
}
