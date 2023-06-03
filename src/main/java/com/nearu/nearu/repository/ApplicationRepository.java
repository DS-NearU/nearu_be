package com.nearu.nearu.repository;

import com.nearu.nearu.entity.Application;

import java.util.List;

public interface ApplicationRepository {
    boolean save(Application a);
    Application fetch(Integer adminNo);

    void update (Application a);
    void delete (Integer adminNo);
    List<Application> fetchAllByAdmin(Integer adminNo);
}
