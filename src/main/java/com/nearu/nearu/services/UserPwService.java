package com.nearu.nearu.services;

import com.nearu.nearu.entity.UserPw;
import com.nearu.nearu.repository.UserPwRepository;

import java.util.*;

public class UserPwService{
    private static Map<Integer, UserPw> userPwMap = new HashMap<>();

    public void save(UserPw u) {
        userPwMap.put(u.getUserNo(), u);
    }

    public UserPw fetch(Integer userNo) {
        return userPwMap.get(userNo);
    }

    public void update(UserPw u) {
        userPwMap.put(u.getUserNo(), u);
    }

    public void delete(Integer userNo) {
        userPwMap.remove(userNo);
    }
}
