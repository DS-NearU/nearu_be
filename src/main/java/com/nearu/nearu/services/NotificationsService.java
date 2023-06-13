package com.nearu.nearu.services;
import com.nearu.nearu.entity.Notifications;
import com.nearu.nearu.repository.NotificationsRepository;

import java.util.*;
public class NotificationsService {
    public static Map<Integer, Notifications> notifMap = new HashMap<>();
    public boolean save(Notifications n) {
        for (Notifications notif : notifMap.values()) {
            if (notif.getUserNo().equals(n.getUserNo())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Notifications fetch(Integer userNo) {
        for (Notifications notif : notifMap.values()) {
            if (notif.getUserNo()==userNo) {
                return notif;
            }
        }
        return null;
    }

    @Override
    public void update(Notifications n) {
        notifMap.put(n.getUserNo(), n);
    }

    @Override
    public void delete(Integer userNo) {
        notifMap.remove(userNo);
    }
}