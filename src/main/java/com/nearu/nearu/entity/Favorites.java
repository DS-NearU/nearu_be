package com.nearu.nearu.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Favorites {
    private Integer favoriteNo;
    private Integer userNo;
    private String address;

    public Favorites() {
    }

    public Favorites(String address) {
        this.address = address;
    }
}
