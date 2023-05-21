package com.nearu.nearu;

import java.util.*;

public class UserPwService implements UserPwRepository{
    private static Map<Integer, UserPw> userPwMap = new HashMap<>();
    @Override
    public void save(UserPw u) {
        userPwMap.put(u.getUserNo(), u);
    }

    @Override
    public UserPw fetch(Integer userNo) {
        return userPwMap.get(userNo);
    }

    @Override
    public void update(UserPw u) {
        userPwMap.put(u.getUserNo(), u);
    }

    @Override
    public void delete(Integer userNo) {
        userPwMap.remove(userNo);
    }
}
