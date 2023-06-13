package com.nearu.nearu.services;

import com.nearu.nearu.OriginObject;
import com.nearu.nearu.entity.*;
import com.nearu.nearu.entity.types.UserType;
import com.nearu.nearu.repository.*;
import com.nearu.nearu.request.FavoritesDto;
import com.nearu.nearu.request.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService extends OriginObject{
    private UserRepository userRepository;
    private UserInfoRepository userInfoRepository;
    private UserPwRepository userPwRepository;
    private NotificationsRepository notificationsRepository;
    private FavoritesRepository favoritesRepository;
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

    public UserInfo fetch(Integer userNo){
        return userInfoRepository.findByUserNo(userNo);
    }

    public void update(UserDto u){
        Integer userNo = userRepository.findByUserId(u.getUserId()).getUserNo();
        UserInfo byUserNo = userInfoRepository.findByUserNo(userNo);
        byUserNo.setName(u.getName());
        byUserNo.setGender(u.getGender());
        byUserNo.setEmail(u.getEmail());
        byUserNo.setPhoneNumber(u.getPhoneNum());
        byUserNo.setEmerPhoneNumber(u.getEmergencyNum());
        byUserNo.setPresentation(u.getPresentation());
        byUserNo.setCondition(u.getCondition());
        byUserNo.setSimilarExp(u.getExperience());
        byUserNo.setPurpose(u.getPurpose());
        userInfoRepository.save(byUserNo);
        Notifications notif = notificationsRepository.findByUserNo(userNo);
        notif.setKakaoNotif(u.getKakaoNotification());
        notif.setEmailNotif(u.getEmailNotification());
        notif.setMsgNotif(u.getMsgNotification());
        notificationsRepository.save(notif);
    }

    public void leave(Integer userNo){
        userInfoRepository.deleteByUserNo(userNo);
        userPwRepository.deleteByUserNo(userNo);
        favoritesRepository.deleteAllByUserNo(userNo);
        notificationsRepository.deleteByUserNo(userNo);
        userRepository.deleteByUserNo(userNo);
    }

    public void updatePw(UserDto u){
        Integer userNo = userRepository.findByUserId(u.getUserId()).getUserNo();
        UserPw password = userPwRepository.findByUserNo(userNo);
        password.setPassword(u.getPassword());
        userPwRepository.save(password);
    }

    public boolean match(String userId, String pw){
        Integer userNo = userRepository.findByUserId(userId).getUserNo();
        if(userPwRepository.findByUserNo(userNo).getPassword().equals(pw))
            return true;
        return false;
    }

    public void updateNotif(UserDto u){

        Integer userNo = userRepository.findByUserId(u.getUserId()).getUserNo();
        Notifications notif = notificationsRepository.findByUserNo(userNo);
        notif.setEmailNotif(u.getEmailNotification());
        notif.setMsgNotif(u.getMsgNotification());
        notif.setKakaoNotif(u.getKakaoNotification());
        notificationsRepository.save(notif);
    }

    public UserDto fetch(String userId){
        User user = userRepository.findByUserId(userId);
        Integer userNo = user.getUserNo();
        UserDto u = new UserDto();
        u.setType(user.getUserType().getType());
        UserInfo info = userInfoRepository.findByUserNo(userNo);
        u.setName(info.getName());
        u.setGender(info.getGender());
        u.setEmail(info.getEmail());
        u.setPhoneNum(info.getPhoneNumber());
        u.setEmergencyNum(info.getEmerPhoneNumber());
        u.setPresentation(info.getPresentation());
        u.setCondition(info.getCondition());
        u.setExperience(info.getSimilarExp());
        u.setPurpose(info.getPurpose());
        Notifications notif = notificationsRepository.findByUserNo(userNo);
        u.setKakaoNotification(notif.getKakaoNotif());
        u.setEmailNotification(notif.getEmailNotif());
        u.setMsgNotification(notif.getMsgNotif());
        return u;
    }

    public void saveFavorites(FavoritesDto f) {
        Favorites favorites = new Favorites();
        favorites.setUserNo(f.getUserNo());
        favorites.setAddress(f.getAddress());
        favoritesRepository.save(favorites);
    }

    public ArrayList<Favorites> fetchAllFavorites (Integer userNo) {
        return favoritesRepository.findAllByUserNo(userNo);
    }
    public void updateFavorites(FavoritesDto fav){
        Integer favoriteNo = fav.getFavoriteNo();
        Favorites favorites = favoritesRepository.findByFavoriteNo(favoriteNo);
        favorites.setAddress(fav.getAddress());
        favoritesRepository.save(favorites);
    }

    public void deleteFavorites (Integer favoriteNo){
        favoritesRepository.deleteByFavoriteNo(favoriteNo);
    }


}