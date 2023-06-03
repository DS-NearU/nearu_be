package com.nearu.nearu.services;
import com.nearu.nearu.entity.Qa;
import com.nearu.nearu.repository.QaRepository;

import java.util.*;
public class QaService implements QaRepository {
    public static Map<Integer, Qa> qaMap = new HashMap<>();
    public static int QA_NUM = 1;
    @Override
    public void save(Qa q) {
        q.setQaNo(QA_NUM);
        qaMap.put(QA_NUM, q);
        QA_NUM++;
    }

    @Override
    public Qa fetch(Integer qaNo) {
        for(Qa q : qaMap.values()){
            if(q.getQaNo()==qaNo){
                return q;
            }
        }
        return null;
    }

    @Override
    public void update(Qa q) {
        qaMap.put(q.getQaNo(), q);
    }

    @Override
    public void delete(Integer qaNo) {
        qaMap.remove(qaNo);
    }

    @Override
    public ArrayList<Qa> readAll() {
        ArrayList<Qa> qaAll = (ArrayList<Qa>) qaMap.values();
        return qaAll;
    }
}
