package com.nearu.nearu.controller;
import com.nearu.nearu.OriginObject;
import com.nearu.nearu.SessionRequest;
import com.nearu.nearu.entity.Favorites;
import com.nearu.nearu.request.FavoritesDto;
import com.nearu.nearu.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class FavoritesController extends OriginObject {
    private final UserService userService;

    @PostMapping("/favorites")
    public void addAddress(SessionRequest request){
        FavoritesDto map = map(request.getParam(), FavoritesDto.class);
        userService.saveFavorites(map);
    }

    @GetMapping("/favorites")
    public ArrayList<Favorites> readAll(SessionRequest request){
        return userService.fetchAllFavorites(request.getSession().getUserNo());
    }

    @DeleteMapping("/favorites")
    public void delete(SessionRequest request){
        FavoritesDto map = map(request.getParam(), FavoritesDto.class);
        userService.deleteFavorites(map.getFavoriteNo());
    }
}
