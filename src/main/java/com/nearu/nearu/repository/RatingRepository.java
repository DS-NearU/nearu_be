package com.nearu.nearu.repository;
import com.nearu.nearu.entity.Rating;

import java.util.*;

public interface RatingRepository {
    void save(Rating r);
    ArrayList<Rating> fetchAll(Integer userNo);

    Rating fetch (Integer ratingNo);

    void delete (Integer r);


}
