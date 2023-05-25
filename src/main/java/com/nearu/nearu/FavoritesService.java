package com.nearu.nearu;
import java.util.*;
public class FavoritesService implements FavoritesRepository{
    public static Map<Integer, Favorites> favoritesMap = new HashMap<>();
    public static int FAV_NUM = 1;
    @Override
    public boolean save(Favorites fav) {
        for(Favorites f : favoritesMap.values()){
            if((fav.getUserNo()==f.getUserNo()) && (f.getAddress().equals(fav.getAddress()))){
                return false;
            }
        }
        fav.setFavoriteNo(FAV_NUM);
        favoritesMap.put(FAV_NUM, fav);
        FAV_NUM++;
        return true;
    }

    @Override
    public Favorites fetch(Integer favoriteNo) {
        for(Favorites f : favoritesMap.values()){
            if(f.getFavoriteNo()==favoriteNo){
                return f;
            }
        }
        return null;
    }

    @Override
    public void update(Favorites fav) {
        favoritesMap.put(fav.getFavoriteNo(), fav);
    }

    @Override
    public void delete(Integer favoriteNo) {
        favoritesMap.remove(favoriteNo);
    }
}
