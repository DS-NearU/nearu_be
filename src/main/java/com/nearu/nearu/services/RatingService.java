package com.nearu.nearu.services;
import com.nearu.nearu.OriginObject;
import com.nearu.nearu.entity.*;
import com.nearu.nearu.repository.RatingRepository;
import com.nearu.nearu.repository.UserInfoRepository;
import com.nearu.nearu.repository.UserRepository;
import com.nearu.nearu.request.RatingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RatingService extends OriginObject {
    private final RatingRepository ratingRepository;
    private final UserInfoRepository userInfoRepository;

    @Transactional
    public void saveRating(RatingDto r){
        Rating rating = new Rating();
        rating.setRating(r.getRating());
        rating.setUserNo(r.getUserNo());
        rating.setComment(r.getComment());
        rating.setApplicationNo(r.getApplicationNo());
        UserInfo userInfo = userInfoRepository.findByUserNo(r.getUserNo());
        userInfo.addRating(r.getRating());
        userInfoRepository.save(userInfo);
        ratingRepository.save(rating);
    }

    public ArrayList<Rating> fetchAllVolunteer(Integer userNo){
        return ratingRepository.findAllByUserNo(userNo);
    }

    public ArrayList<Rating> fetchAllNeeder(Integer adminNo){
        return ratingRepository.findAllByApplication_AdminNo(adminNo);
    }

    @Transactional
    public void delete(RatingDto r){
        UserInfo userInfo = userInfoRepository.findByUserNo(r.getUserNo());
        userInfo.removeRating(r.getRating());
        userInfoRepository.save(userInfo);
        ratingRepository.deleteByRatingNo(r.getRatingNo());
    }
}
