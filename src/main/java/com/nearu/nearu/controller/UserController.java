package com.nearu.nearu.controller;

import com.nearu.nearu.OriginObject;
import com.nearu.nearu.SessionRequest;
import com.nearu.nearu.config.flows.SessionMapper;
import com.nearu.nearu.entity.Notifications;
import com.nearu.nearu.entity.User;
import com.nearu.nearu.entity.UserInfo;
import com.nearu.nearu.entity.UserPw;
import com.nearu.nearu.entity.types.UserType;
import com.nearu.nearu.request.FavoritesDto;
import com.nearu.nearu.request.UpdateAdminRequest;
import com.nearu.nearu.request.UserDto;
import com.nearu.nearu.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.Session;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

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

    @SessionMapper
    @GetMapping("/sign-in")
    public User signIn(SessionRequest request){
        UserDto map = map(request.getParam(), UserDto.class);
        User match = userService.match(map.getUserId(), map.getPassword());
        return match;
    }
    @SessionMapper
    @GetMapping("/profile")
    public UserDto viewProfile (SessionRequest request) {
        UserDto map = map(request.getParam(), UserDto.class);
        return userService.fetch(map.getUserId());
    }

    @SessionMapper
    @Transactional
    @PutMapping("/profile")
    public void editProfile (SessionRequest request) {
        UserDto map = map(request.getParam(), UserDto.class);
        userService.update(map);
    }

    @SessionMapper
    @Transactional
    @PutMapping("/notifications")
    public void editNotif (SessionRequest request) {
        UpdateAdminRequest map = map(request.getParam(), UpdateAdminRequest.class);
        userService.updateNotif(map);
    }

    @SessionMapper
    @Transactional
    @DeleteMapping("/leave")
    public void leave (SessionRequest request) {
        UserDto map = map(request.getParam(), UserDto.class);
        userService.leave(map.getUserId());
    }

    @SessionMapper
    @Transactional
    @PutMapping("/update-pw")
    public void editPw(SessionRequest request){
        UserDto map = map(request.getParam(), UserDto.class);
        userService.updatePw(map);
    }

}
