package com.nearu.nearu;

public interface ApplicationRepository {
    boolean save(Application a);
    Application fetch(Integer adminNo);
    void update (Application ㅁ);
    void delete (Integer adminNo);
}
