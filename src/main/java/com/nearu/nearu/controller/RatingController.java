package com.nearu.nearu.controller;
import com.nearu.nearu.OriginObject;
import com.nearu.nearu.SessionRequest;
import com.nearu.nearu.config.flows.SessionMapper;
import com.nearu.nearu.request.RatingDto;
import com.nearu.nearu.request.UserDto;
import com.nearu.nearu.services.RatingService;
import com.nearu.nearu.entity.Rating;
import com.nearu.nearu.entity.UserInfo;
import com.nearu.nearu.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;


@RestController
@RequiredArgsConstructor
public class RatingController extends OriginObject {

    private final RatingService ratingService;
    private final UserService userService;

    @SessionMapper
    @Transactional
    @PostMapping("/rating")
    public void create(SessionRequest request){
        RatingDto map = map(request.getParam(), RatingDto.class);
        ratingService.saveRating(map);
        // set --> save --> update
    }

    @SessionMapper
    @GetMapping("/rate-all")
    public ArrayList<Rating> readAll(SessionRequest request){
        RatingDto map = map(request.getParam(), RatingDto.class);
        return ratingService.fetchAllNeeder(map.getUserId());
    }

    @SessionMapper
    @Transactional
    @DeleteMapping("/rating")
    public void delete(SessionRequest request){
        RatingDto map = map(request.getParam(), RatingDto.class);
        ratingService.delete(map);
    }
}
