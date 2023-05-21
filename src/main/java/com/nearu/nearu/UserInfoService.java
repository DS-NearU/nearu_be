package com.nearu.nearu;

import java.util.*;
public class UserInfoService implements UserInfoRepository{
    public static Map<Integer, UserInfo> userInfoMap = new HashMap<>();

    @Override
    public void save(UserInfo info) {
        userInfoMap.put(info.getUserNo(), info);
    }

    @Override
    public UserInfo fetch(Integer userNo) {
        return userInfoMap.get(userNo);
    }

    @Override
    public void update(UserInfo info) {
        userInfoMap.put(info.getUserNo(), info);
    }

    @Override
    public void delete(Integer userNo) {
        userInfoMap.remove(userNo);
    }
}
