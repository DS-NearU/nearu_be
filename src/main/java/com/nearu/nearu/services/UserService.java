package com.nearu.nearu.services;

import com.nearu.nearu.OriginObject;
import com.nearu.nearu.entity.*;
import com.nearu.nearu.entity.types.UserType;
import com.nearu.nearu.repository.*;
import com.nearu.nearu.request.FavoritesDto;
import com.nearu.nearu.request.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService extends OriginObject{
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final UserPwRepository userPwRepository;
    private final NotificationsRepository notificationsRepository;
    private final FavoritesRepository favoritesRepository;

    @Transactional
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUserType(UserType.getType(userDto.getType()));
        UserInfo userInfo = new UserInfo();
        userInfo.setAddress(userDto.getAddress());
        userInfo.setName(userDto.getName());
        userInfo.setGender(userDto.getGender());
        userInfo.setCondition(userDto.getCondition());
        userInfo.setEmail(userDto.getEmail());
        userInfo.setPurpose(userDto.getPurpose());
        userInfo.setEmerPhoneNumber(userDto.getEmergencyNum());
        userInfo.setPhoneNumber(userDto.getPhoneNum());
        userInfo.setPresentation(userDto.getPresentation());
        userInfo.setSimilarExp(userDto.getExperience());
        UserPw userPw = new UserPw();
        userPw.setPassword(userDto.getPassword());
        userRepository.save(user);//db에저장
        userInfo.setUserNo(user.getUserNo());
        userPw.setUserNo(user.getUserNo());
        userPwRepository.save(userPw);
        userInfoRepository.save(userInfo);
        Notifications notif = new Notifications();
        notif.setEmailNotif(false);
        notif.setKakaoNotif(false);
        notif.setMsgNotif(false);
        notif.setUserNo(user.getUserNo());
        notificationsRepository.save(notif);
    }

    @Transactional
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

    @Transactional
    public void leave(String userId){
        Integer userNo = userRepository.findByUserId(userId).getUserNo();
        userInfoRepository.deleteByUserNo(userNo);
        userPwRepository.deleteByUserNo(userNo);
        favoritesRepository.deleteAllByUserNo(userNo);
        notificationsRepository.deleteByUserNo(userNo);
        userRepository.deleteByUserNo(userNo);
    }

    @Transactional
    public void updatePw(UserDto u){
        Integer userNo = userRepository.findByUserId(u.getUserId()).getUserNo();
        UserPw password = userPwRepository.findByUserNo(userNo);
        password.setPassword(u.getPassword());
        userPwRepository.save(password);
    }

    public User match(String userId, String pw){
        User user = userRepository.findByUserId(userId);
        if(userPwRepository.findByUserNo(user.getUserNo()).getPassword().equals(pw))
            return user;
        return null;
    }

    @Transactional
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
        u.setUserId(userId);
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
        u.setAddress(info.getAddress());
        Notifications notif = notificationsRepository.findByUserNo(userNo);
        u.setKakaoNotification(notif.getKakaoNotif());
        u.setEmailNotification(notif.getEmailNotif());
        u.setMsgNotification(notif.getMsgNotif());
        return u;
    }

    @Transactional
    public void saveFavorites(FavoritesDto f) {
        Favorites favorites = new Favorites();
        Integer userNo = userRepository.findByUserId(f.getUserId()).getUserNo();
        favorites.setUserNo(userNo);
        favorites.setAddress(f.getAddress());
        favoritesRepository.save(favorites);
    }

    public ArrayList<Favorites> fetchAllFavorites (String userId) {
        Integer userNo = userRepository.findByUserId(userId).getUserNo();
        return favoritesRepository.findAllByUserNo(userNo);
    }

    @Transactional
    public void updateFavorites(FavoritesDto fav){
        Integer favoriteNo = fav.getFavoriteNo();
        Favorites favorites = favoritesRepository.findByFavoriteNo(favoriteNo);
        favorites.setAddress(fav.getAddress());
        favoritesRepository.save(favorites);
    }

    @Transactional
    public void deleteFavorites (Integer favoriteNo){
        favoritesRepository.deleteByFavoriteNo(favoriteNo);
    }


}
