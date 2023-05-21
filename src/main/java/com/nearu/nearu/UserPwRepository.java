package com.nearu.nearu;

public interface UserPwRepository {
    void save(UserPw u);
    UserPw fetch(Integer userNo);
    void update(UserPw u);
    void delete(Integer userNo);
}
