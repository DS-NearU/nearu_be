package com.nearu.nearu.repository;

import com.nearu.nearu.entity.Notifications;

public interface NotificationsRepository {
    boolean save(Notifications n);
    Notifications fetch(Integer userNo);
    void update (Notifications n);
    void delete (Integer userNo);
}
