package com.nearu.nearu;

public interface UserRepository {
    boolean save(User u);
    User fetch(String userId);
    void update (User u);
    void delete (Integer userNo);
}
