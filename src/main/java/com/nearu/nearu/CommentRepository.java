package com.nearu.nearu;

public interface CommentRepository
{
    void save (Comment c);
    Comment fetch (Integer commentNo);
    void update (Comment c);
    void delete (Integer commentNo);
    void deleteAll(Integer qaNo);
}
