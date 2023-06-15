package com.nearu.nearu.controller;
import com.nearu.nearu.OriginObject;
import com.nearu.nearu.services.RatingService;
import com.nearu.nearu.entity.Rating;
import com.nearu.nearu.entity.UserInfo;
import com.nearu.nearu.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class RatingController extends OriginObject {

    private final RatingService ratingService;
    private final UserService userService;

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
