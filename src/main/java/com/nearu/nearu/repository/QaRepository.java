package com.nearu.nearu.repository;

import com.nearu.nearu.entity.Qa;

import java.util.ArrayList;

public interface QaRepository {
    void save(Qa q);
    Qa fetch(Integer qaNo);
    void update(Qa q);
    void delete(Integer qaNo);
    public ArrayList<Qa> readAll();
}
