package com.nearu.nearu;

import java.util.*;

public class UserService implements UserRepository{
    private Map<Integer, User> userMap = new HashMap<>();
    public static int USER_NUM = 1;
    @Override
    public boolean save(User u) {
        for (User user : userMap.values()) {
            if (user.getUserId().equals(u.getUserId())) {
                return false;
            }
        }
        u.setUserNo(USER_NUM);
        userMap.put(USER_NUM, u);
        USER_NUM++;
        return true;
    }

    @Override
    public User fetch(String s) {
        for (User user : userMap.values()) {
            if (user.getUserId().equals(s)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void update(User u) {
        userMap.put(u.getUserNo(), u);
    }

    @Override
    public void delete(Integer userNo) {
        userMap.remove(userNo);
    }
}
