package com.nearu.nearu;

public interface UserInfoRepository {
    void save(UserInfo info);
    UserInfo fetch(Integer userNo);
    void update (UserInfo info);
    void delete (Integer userNo);

}
