package com.nearu.nearu;

public interface ApplicationRepository {
    boolean save(Application a);
    Application fetch(String userId);
    void update (String userId, User u);
    void delete (String userId);
}
