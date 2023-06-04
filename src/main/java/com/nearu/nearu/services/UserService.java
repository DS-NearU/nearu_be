package com.nearu.nearu.services;

import com.nearu.nearu.OriginObject;
import com.nearu.nearu.UserType;
import com.nearu.nearu.entity.User;
import com.nearu.nearu.entity.UserInfo;
import com.nearu.nearu.entity.UserPw;
import com.nearu.nearu.repository.UserInfoRepository;
import com.nearu.nearu.repository.UserPwRepository;
import com.nearu.nearu.repository.UserRepository;
import com.nearu.nearu.request.UserDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService extends OriginObject{
    private UserRepository userRepository;
    private UserInfoRepository userInfoRepository;
    private UserPwRepository userPwRepository;
//    @Override
//    public boolean save(User u) {
//        for (User user : userMap.values()) {
//            if (user.getUserId().equals(u.getUserId())) {
//                return false;
//            }
//        }
//        u.setUserNo(USER_NUM);
//        userMap.put(USER_NUM, u);
//        USER_NUM++;
//        return true;
//    }
//
    public void saveUser(UserDto userDto){
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUserType(UserType.getType(userDto.getType()));
        UserInfo userInfo = new UserInfo();


        UserPw userPw = new UserPw();


        userRepository.save(user);//db에저장
        userInfo.setUserNo(user.getUserNo());
        userPw.setUserNo(user.getUserNo());
        userPwRepository.save(userPw);
        userInfoRepository.save(userInfo);
    }

//    @Override
//    public User fetch(String s) {
//        for (User user : userMap.values()) {
//            if (user.getUserId().equals(s)) {
//                return user;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public void update(User u) {
//        userMap.put(u.getUserNo(), u);
//    }
//
//    @Override
//    public void delete(Integer userNo) {
//        userMap.remove(userNo);
//    }
}
