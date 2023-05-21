package com.nearu.nearu;

public interface UserRepository {
    boolean save(User u);
    User fetch(String userId);
    void update (String userId, User u);
    void delete (String userId);
}
