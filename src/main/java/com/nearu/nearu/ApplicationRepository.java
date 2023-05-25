package com.nearu.nearu;

import java.util.List;

public interface ApplicationRepository {
    boolean save(Application a);
    Application fetch(Integer adminNo);
    void update (Application a);
    void delete (Integer adminNo);
    List<Application> fetchAllByAdmin(Integer adminNo);
}
