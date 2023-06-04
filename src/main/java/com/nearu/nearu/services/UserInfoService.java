package com.nearu.nearu.services;

import com.nearu.nearu.entity.UserInfo;
import com.nearu.nearu.repository.UserInfoRepository;

import java.util.*;
public class UserInfoService{
    public static Map<Integer, UserInfo> userInfoMap = new HashMap<>();

    @Override
    public void save(UserInfo info) {
        userInfoMap.put(info.getUserNo(), info);
    }

    @Override
    public UserInfo fetch(Integer userNo) {
        return userInfoMap.get(userNo);
    }
    public ArrayList<UserInfo> fetch(ArrayList<Integer> userNo) {
        ArrayList<UserInfo> userInfos = new ArrayList<>();
        for(Integer n : userNo) {
            userInfos.add(userInfoMap.get(n));
        }
        return userInfos;
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
