package com.nearu.nearu.repository;

import com.nearu.nearu.entity.User;

public interface UserRepository {
    boolean save(User u);
    User fetch(String userId);
    void update (User u);
    void delete (Integer userNo);
}
