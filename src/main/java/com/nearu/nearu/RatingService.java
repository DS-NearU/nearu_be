package com.nearu.nearu;
import java.util.*;

public class RatingService implements RatingRepository {

    public static Map<Integer, Rating> ratingMap = new HashMap<>();

    @Override
    public void save(Rating r) {

        ratingMap.put(r.getUserNo(), r);
    }

    @Override
    public Rating fetch(Integer userNo) {
        return ratingMap.get(userNo);
    }

    @Override
    public void update(Rating r) {
        ratingMap.put(r.getApplicationNo(), r);
    }
}
