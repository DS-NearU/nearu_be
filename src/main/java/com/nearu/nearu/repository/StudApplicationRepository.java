package com.nearu.nearu.repository;
import com.nearu.nearu.entity.StudApplication;

import java.util.*;

public interface StudApplicationRepository {
    void save(StudApplication s);
    ArrayList<StudApplication> fetch(Integer applicationNo);

    StudApplication fetch (Integer applicationNo, Integer userNo);
    void update(StudApplication u);

    void delete(Integer applicationNo, Integer userNo);

    void deleteAllByApplication(Integer applicationNo);

}
