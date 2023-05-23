package com.nearu.nearu;

public interface RatingRepository {
    void save(Rating r);
    Rating fetch(Integer userNo);
    void update(Rating r);

}
