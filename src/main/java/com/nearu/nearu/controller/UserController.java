package com.nearu.nearu.controller;

import com.nearu.nearu.OriginObject;
import com.nearu.nearu.SessionRequest;
import com.nearu.nearu.config.flows.SessionMapper;
import com.nearu.nearu.entity.Notifications;
import com.nearu.nearu.entity.User;
import com.nearu.nearu.entity.UserInfo;
import com.nearu.nearu.entity.UserPw;
import com.nearu.nearu.entity.types.UserType;
import com.nearu.nearu.request.UserDto;
import com.nearu.nearu.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.Session;
import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
public class UserController extends OriginObject {

    private final UserService userService;

    @SessionMapper
    @Transactional
    @PostMapping("/sign-up")
    public void signUp(SessionRequest request) {
        UserDto map = map(request.getParam(), UserDto.class);
        userService.saveUser(map);
    }


    @GetMapping("/sign-in")
    public boolean signIn(SessionRequest request){
        UserDto map = map(request.getParam(), UserDto.class);
        return userService.match(map.getUserId(), map.getPassword());
    }

    @GetMapping("/profile")
    public UserInfo viewProfile (SessionRequest request) {
        return userService.fetch(request.getSession().getUserNo());
    }

    @PutMapping("/profile")
    public void editProfile (SessionRequest request) {
        UserDto map = map(request.getParam(), UserDto.class);
        userService.update(map);
    }

    @DeleteMapping("/leave")
    public void leave (SessionRequest request) {
        userService.leave(request.getSession().getUserNo());
    }

    @PutMapping("/update-pw")
    public void editPw(SessionRequest request){
        UserDto map = map(request.getParam(), UserDto.class);
        userService.updatePw(map);
    }

}
