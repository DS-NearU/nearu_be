package com.nearu.nearu;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserRepository{
    private List<User> userList = new ArrayList<User>();
    public static int USER_NUM = 1;
    @Override
    public boolean save(User u) {
        for (int i=0; i<userList.size(); i++) {
            if (userList.get(i).getUserId().equals(u.getUserId())) {
                return false;
            }
        }
        u.setUserNo(USER_NUM);
        userList.add(u);
        USER_NUM++;
        return true;
    }

    @Override
    public User fetch(String s) {
        for (int i=0; i<userList.size(); i++) {
            if (userList.get(i).getUserId().equals(s)) {
                return userList.get(i);
            }
        }
        return null;
    }

    @Override
    public void update(String s, User u) {
        for (int i=0; i<userList.size(); i++) {
            if (userList.get(i).getUserId().equals(s)) {
                userList.set(i, u);
            }
        }
    }

    @Override
    public void delete(String s) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserId().equals(s)) {
                userList.remove(i);
            }
        }
    }
}
