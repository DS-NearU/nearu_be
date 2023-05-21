package com.nearu.nearu;

public class UserController {

    private final UserService userService = new UserService();
    private final UserInfoService userInfoService = new UserInfoService();
    private final UserPwService userPwService = new UserPwService();

    public void signUp(){

    }

    public boolean signIn(String userId, String pw){
        Integer n = userService.fetch(userId).getUserNo();
        if(userPwService.fetch(n).getPassword().equals(pw))
        {
            return true;
        }
        return false;
    }


}
