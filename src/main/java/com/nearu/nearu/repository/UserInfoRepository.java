package com.nearu.nearu.repository;

import com.nearu.nearu.entity.UserInfo;

public interface UserInfoRepository {
    void save(UserInfo info);
    UserInfo fetch(Integer userNo);
    void update (UserInfo info);
    void delete (Integer userNo);

}
