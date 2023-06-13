package com.nearu.nearu.controller;
import com.nearu.nearu.entity.Favorites;

import java.util.*;

public class FavoritesController {
    private final FavoritesService favoritesService = new FavoritesService();

    public void addAddress(Integer userNo, String address){
        Favorites f = new Favorites();
        f.setUserNo(userNo);
        f.setAddress(address);
        favoritesService.save(f);
    }

    public ArrayList<Favorites> readAll(Integer userNo){
        return favoritesService.fetchAll(userNo);
    }

    public void delete(Integer favoriteNo){
        favoritesService.delete(favoriteNo);
    }
}
