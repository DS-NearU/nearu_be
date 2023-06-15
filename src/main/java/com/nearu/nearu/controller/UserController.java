package com.nearu.nearu.controller;

import com.nearu.nearu.OriginObject;
import com.nearu.nearu.SessionRequest;
import com.nearu.nearu.entity.Notifications;
import com.nearu.nearu.entity.User;
import com.nearu.nearu.entity.UserInfo;
import com.nearu.nearu.entity.UserPw;
import com.nearu.nearu.entity.types.UserType;
import com.nearu.nearu.request.UserDto;
import com.nearu.nearu.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController extends OriginObject {

    private final UserService userService;

    @PostMapping("/sign-up")
    public void signUp(SessionRequest request) {
        UserDto map = map(request.getParam(), UserDto.class);
        userService.saveUser(map);
    }


    public boolean signIn(String userId, String pw){
        Integer n = userService.fetch(userId).getUserNo();
        if(userPwService.fetch(n).getPassword().equals(pw))
        {
            return true;
        }
        return false;
    }

    public UserInfo viewProfile (String userId) {
        Integer n = userService.fetch(userId).getUserNo();
        return userInfoService.fetch(n);
    }

    public void editProfile (String userId, String pw, String name, Boolean gender, String email, String phNum, String emNum, String present, String cond, String simExp, Boolean purpose, Boolean emailNotif, Boolean msgNotif, Boolean kakaoNotif) {
        Integer n = userService.fetch(userId).getUserNo();

        UserInfo info = new UserInfo(name, gender, email, phNum, emNum, present, cond, simExp, purpose);
        info.setUserNo(n);
        userInfoService.update(info);
        UserPw p = new UserPw(pw);
        p.setUserNo(n);
        userPwService.update(p);
        Notifications no = new Notifications(emailNotif, msgNotif, kakaoNotif);
        no.setUserNo(n);
        notifService.save(no);

    }

    public void leave (String userId) {
        Integer n = userService.fetch(userId).getUserNo();
        userInfoService.delete(n);
        userPwService.delete(n);
        userService.delete(n);
        notifService.delete(n);
    }



}
