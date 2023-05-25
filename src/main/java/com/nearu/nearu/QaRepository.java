package com.nearu.nearu;

public interface QaRepository {
    void save(Qa q);
    Qa fetch(Integer qaNo);
    void update(Qa q);
    void delete(Integer qaNo);
}
