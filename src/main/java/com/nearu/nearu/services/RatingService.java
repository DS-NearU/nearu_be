package com.nearu.nearu.services;
import com.nearu.nearu.entity.Rating;
import com.nearu.nearu.repository.RatingRepository;

import java.util.*;

public class RatingService implements RatingRepository {

    public static Map<Integer, Rating> ratingMap = new HashMap<>();

    public static int RATE_NUM = 1;

    @Override
    public void save(Rating r) {
        for(Rating rate : ratingMap.values()){
            if((rate.getApplicationNo().equals(r.getApplicationNo())) && (rate.getUserNo().equals(r.getUserNo()))) {
                return;
            }
        }
        r.setUserNo(RATE_NUM);
        ratingMap.put(RATE_NUM, r);
        RATE_NUM++;
    }

    @Override
    public Rating fetch (Integer ratingNo){
        return ratingMap.get(ratingNo);
    }
    @Override
    public ArrayList<Rating> fetchAll(Integer userNo) {
        ArrayList<Rating> allRatings = new ArrayList<Rating>();
        for (Rating r : ratingMap.values()) {
            if (r.getUserNo().equals(userNo)) {
                allRatings.add(r);
            }
        }
        return allRatings;
    }

    @Override
    public void delete(Integer r) {
        ratingMap.remove(r);
    }
}
