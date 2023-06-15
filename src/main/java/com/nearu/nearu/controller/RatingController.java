package com.nearu.nearu.controller;
import com.nearu.nearu.services.RatingService;
import com.nearu.nearu.entity.Rating;
import com.nearu.nearu.entity.UserInfo;

import java.util.*;
public class RatingController{

    private final RatingService ratingService = new RatingService();
    private final UserInfoService userInfoService = new UserInfoService();

    public void create(Integer rating, String comment, Integer userNo, Integer applicationNo){
        Rating rate = new Rating (rating, comment);
        rate.setUserNo(userNo);
        rate.setApplicationNo(applicationNo);
        ratingService.save(rate);
        UserInfo userInfo = userInfoService.fetch(userNo);
        userInfo.addRating(rating); // method created in UserInfo.java
        userInfoService.update(userInfo);
        // set --> save --> update
    }

    public ArrayList<Rating> readAll(Integer userNo){
        return ratingService.fetchAll(userNo);
    }

    public void delete(Integer ratingNo){
        Rating r = ratingService.fetch(ratingNo);
        Integer rate = r.getRating();
        UserInfo userInfo = userInfoService.fetch(r.getUserNo());
        userInfo.removeRating(rate);
        ratingService.delete(ratingNo);
    }







}
