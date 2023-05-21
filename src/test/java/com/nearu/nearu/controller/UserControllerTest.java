package com.nearu.nearu.controller;

import com.nearu.nearu.UserController;
import com.nearu.nearu.UserInfoService;
import com.nearu.nearu.UserPwService;
import com.nearu.nearu.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
