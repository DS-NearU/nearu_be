package com.nearu.nearu;

public interface NotificationsRepository {
    boolean save(Notifications n);
    Notifications fetch(Integer userNo);
    void update (Notifications n);
    void delete (Integer userNo);
}
