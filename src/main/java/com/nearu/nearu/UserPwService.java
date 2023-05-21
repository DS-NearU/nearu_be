package com.nearu.nearu;

import java.util.ArrayList;
import java.util.List;

public class UserPwService implements UserPwRepository{
    private List<UserPw> userPwList = new ArrayList<UserPw>();
    @Override
    public void save(UserPw u) {
        userPwList.add(u);
    }

    @Override
    public UserPw fetch(Integer userNo) {
        for (int i=0; i<userPwList.size(); i++) {
            if (userPwList.get(i).getUserNo()==userNo) {
                return userPwList.get(i);
            }
        }
        return null;
    }

    @Override
    public void update(UserPw u) {
        for (int i=0; i<userPwList.size(); i++) {
            if (userPwList.get(i).getUserNo()== u.getUserNo()) {
                userPwList.set(i, u);
            }
        }
    }

    @Override
    public void delete(Integer userNo) {
        for (int i = 0; i < userPwList.size(); i++) {
            if (userPwList.get(i).getUserNo()==userNo) {
                userPwList.remove(i);
            }
        }
    }
}
