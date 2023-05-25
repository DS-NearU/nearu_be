package com.nearu.nearu;

import java.util.*;

public class ApplicationService implements ApplicationRepository{
    public static Map<Integer, Application> appMap = new HashMap<>();
    public static int APP_NUM = 1;

    @Override
    public boolean save(Application a) {
        a.setAdminNo(APP_NUM);
        appMap.put(APP_NUM, a);
        APP_NUM++;
        return true;
    }

    @Override
    public Application fetch(Integer applicatiionNo) {
        for (Application app : appMap.values()) {
            if (app.getApplicationNo() == applicatiionNo) {
                return app;
            }
        }
        return null;
    }

    @Override
    public List<Application> fetchAllByAdmin(Integer adminNo) {
        List<Application>  toReturn =   new ArrayList<>();
        for (Application app : appMap.values()) {
            if (app.getAdminNo() == adminNo) {
                toReturn.add(app);
            }
        }
        return toReturn;
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
