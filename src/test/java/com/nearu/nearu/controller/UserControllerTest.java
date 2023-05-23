package com.nearu.nearu.controller;

import com.nearu.nearu.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class UserControllerTest {

    UserController userController = new UserController();
    UserService userService = new UserService();
    UserInfoService userInfo = new UserInfoService();
    UserPwService userPw = new UserPwService();

    @Test
    void signUpTest(){
        userController.signUp("abc", "abc", true, "dong", true, "abc@gmail", "222", "232",  "abc", "abc", "abc", false);

        // UserService.userMap.get();
        Assertions.assertEquals("abc", userService.fetch("abc").getUserId());
        UserInfo ui = userInfo.fetch(userService.fetch("abc").getUserNo());
        Map<Integer, UserInfo> userInfoMap = UserInfoService.userInfoMap;
        //Assertions.assertNotNull(userService.fetch("abc").getUserNo());
        Assertions.assertEquals("abc@gmail", userInfo.fetch(userService.fetch("abc").getUserNo()).getEmail());
    }

    /*
    void signInTest() {

    }
    UserInfo viewProfileTest() {

    }

    void editProfile() {

    }

    void leave() {

    }
*/
}