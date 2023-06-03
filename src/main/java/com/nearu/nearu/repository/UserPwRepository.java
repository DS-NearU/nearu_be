package com.nearu.nearu.repository;

import com.nearu.nearu.entity.UserPw;

public interface UserPwRepository {
    void save(UserPw u);
    UserPw fetch(Integer userNo);
    void update(UserPw u);
    void delete(Integer userNo);
}
