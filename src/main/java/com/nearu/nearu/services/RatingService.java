package com.nearu.nearu.services;
import com.nearu.nearu.OriginObject;
import com.nearu.nearu.entity.Rating;
import com.nearu.nearu.repository.RatingRepository;
import com.nearu.nearu.request.RatingDto;

import java.util.ArrayList;

import java.util.*;

public class RatingService extends OriginObject {
    private RatingRepository ratingRepository;

    public void saveRating(RatingDto r){
        Rating rating = new Rating();
        rating.setRating(r.getRating());
        rating.setUserNo(r.getUserNo());
        rating.setComment(r.getComment());
        rating.setApplicationNo(r.getApplicationNo());
        ratingRepository.save(rating);
    }

    public ArrayList<Rating> fetchAllVolunteer(Integer userNo){
        return ratingRepository.findAllByUserNo(userNo);
    }

    public ArrayList<Rating> fetchAllNeeder(Integer applicationNo){

    }
}
