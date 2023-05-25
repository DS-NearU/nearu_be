package com.nearu.nearu;

import java.util.*;

public class ApplicationService implements ApplicationRepository{
    public static Map<Integer, Application> appMap = new HashMap<>();
    public static int APP_NUM = 1;
    @Override
    public boolean save(Application a) {
        for (Application app : appMap.values()) {
            if (app.getAdminNo().equals(a.getAdminNo())) {
                return false;
            }
        }
        a.setAdminNo(APP_NUM);
        appMap.put(APP_NUM, a);
        APP_NUM++;
        return true;
    }

    @Override
    public Application fetch(Integer adminNo) {
        for (Application app : appMap.values()) {
            if (app.getAdminNo()==adminNo) {
                return app;
            }
        }
        return null;
    }

    @Override
    public void update(Application a) {
        appMap.put(a.getAdminNo(), a);
    }

    @Override
    public void delete(Integer adminNo) {
        appMap.remove(adminNo);
    }
}
