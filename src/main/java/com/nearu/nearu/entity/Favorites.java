package com.nearu.nearu.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "favorites")
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_no")
    private Integer favoriteNo;

    @ManyToOne
    @Column(name = "user_no")
    private Integer userNo;

    @Column(name = "address")
    private String address;

    public Favorites() {
    }

    public Favorites(String address) {
        this.address = address;
    }
}
