package com.nearu.nearu;

import java.util.HashMap;
import java.util.Map;
import java.util.*;


public class StudApplicationService implements StudApplicationRepository {
    public static Map<StudentApplicationPk, StudApplication> studAppMap = new HashMap<>();

    @Override
    public void save(StudApplication s) {
        StudentApplicationPk pk = new StudentApplicationPk();
        pk.setApplicationNo(s.getApplicationNo());
        pk.setUserNo(s.getUserNo());
        for (StudentApplicationPk sapk : studAppMap.keySet()){
            if(sapk.equals(pk))
                return ;
        }
        studAppMap.put(pk, s);
    }

    @Override
    public ArrayList<StudApplication> fetch(Integer applicationNo) {
        ArrayList<StudApplication> applications = new ArrayList<StudApplication>();

        for (StudentApplicationPk pk : studAppMap.keySet()) {
            if (pk.getApplicationNo() == applicationNo) {
                applications.add(studAppMap.get(pk));
            }
        }
        return applications;
    }

    public StudApplication fetch (Integer applicationNo, Integer userNo) {
        StudentApplicationPk pk = new StudentApplicationPk();
        pk.setApplicationNo(applicationNo);
        pk.setUserNo(userNo);
        return studAppMap.get(pk);
    }


    @Override
    public void update(StudApplication s) {
        StudentApplicationPk pk = new StudentApplicationPk();
        pk.setApplicationNo(s.getApplicationNo());
        pk.setUserNo(s.getUserNo());
        studAppMap.put(pk, s);

    }

    @Override
    public void delete(Integer applicationNo, Integer userNo) {
        StudentApplicationPk pk = new StudentApplicationPk();
        pk.setApplicationNo(applicationNo);
        pk.setUserNo(userNo);
        studAppMap.remove(pk);
    }


    @Override
    public void deleteAllByApplication(Integer applicationNo) {
        for (StudentApplicationPk sapk : studAppMap.keySet()){
            if(sapk.getApplicationNo() == applicationNo)
                studAppMap.remove(sapk);
        }
    }
}
