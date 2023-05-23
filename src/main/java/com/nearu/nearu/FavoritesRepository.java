package com.nearu.nearu;

public interface FavoritesRepository {
    boolean save(Favorites fav);
    Favorites fetch(Integer favoriteNo);
    void update(Favorites fav);
    void delete(Integer favoriteNo);
}
