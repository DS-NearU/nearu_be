package com.nearu.nearu;

public class UserController {

    private final UserService userService = new UserService();
    private final UserInfoService userInfoService = new UserInfoService();
    private final UserPwService userPwService = new UserPwService();

    public void signUp(String userId, String pw, Boolean type, String name, Boolean gender, String email, String phNum, String emNum, String present, String cond, String simExp, Boolean purpose) {
        User u = new User(type, userId);
        if (userService.save(u)) { //passing by ref / passing by val
            UserInfo info = new UserInfo(name, gender, email, phNum, emNum, present, cond, simExp, purpose);
            info.setUserNo(u.getUserNo());
            userInfoService.save(info);
            UserPw p = new UserPw(pw);
            p.setUserNo(u.getUserNo());
            userPwService.save(p);
        }
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

    public void editProfile (String userId, String pw, String name, Boolean gender, String email, String phNum, String emNum, String present, String cond, String simExp, Boolean purpose) {
        Integer n = userService.fetch(userId).getUserNo();

        UserInfo info = new UserInfo(name, gender, email, phNum, emNum, present, cond, simExp, purpose);
        info.setUserNo(n);
        userInfoService.update(info);
        UserPw p = new UserPw(pw);
        p.setUserNo(n);
        userPwService.update(p);

    }

    public void leave (String userId) {
        Integer n = userService.fetch(userId).getUserNo();
        userInfoService.delete(n);
        userPwService.delete(n);
        userService.delete(n);
    }



}
