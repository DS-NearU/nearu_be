package com.nearu.nearu;

import java.util.ArrayList;

public interface FavoritesRepository {
    boolean save(Favorites fav);
    Favorites fetch(Integer favoriteNo);
    ArrayList<Favorites> fetchAll(Integer userNo);
    void update(Favorites fav);
    void delete(Integer favoriteNo);
}
