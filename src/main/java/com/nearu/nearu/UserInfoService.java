package com.nearu.nearu;

import java.util.*;
public class UserInfoService implements UserInfoRepository{
    private List <UserInfo> userInfoList = new ArrayList<UserInfo>();


    @Override
    public void save(UserInfo info) {
        userInfoList.add(info);
    }

    @Override
    public UserInfo fetch(Integer userNo) {
        for (int i=0; i<userInfoList.size(); i++) {
            if (userInfoList.get(i).getUserNo() == userNo) {
                return userInfoList.get(i);
            }
        }
        return null;    }

    @Override
    public void update(UserInfo info) {
        for (int i=0; i<userInfoList.size(); i++) {
            if (userInfoList.get(i).getUserNo() == info.getUserNo()) {
                userInfoList.set(i, info);
            }
        }

    }

    @Override
    public void delete(Integer userNo) {
        for (int i=0; i<userInfoList.size(); i++) {
            if (userInfoList.get(i).getUserNo() == userNo) {
                userInfoList.remove(i);
            }
        }

    }
}
