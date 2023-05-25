package com.nearu.nearu;

import java.util.HashMap;
import java.util.Map;

public class StudApplicationService implements StudApplicationRepository {
    public static Map<Integer, StudApplication> studAppMap = new HashMap<>();

    @Override
    public void save(StudApplication s) {
        // user의 application
        // user의 application을
        for (StudApplication a : studAppMap.values()) {
            if (a.getStatus() == false) {
                studAppMap.put(s.getUserNo(), s);
            }
        }
    }

    @Override
    public StudApplication fetch(Integer applicationNo) {
        for (StudApplication a : studAppMap.values()) {
            if (a.getApplicationNo() == applicationNo) {
                return a;
            }
        }
        return null;
    }

    @Override
    public void update(StudApplication u) {
        studAppMap.put(u.getApplicationNo(), u);

    }

    @Override
    public void delete(Integer applicationNo) {
        studAppMap.remove(applicationNo);

    }
}
