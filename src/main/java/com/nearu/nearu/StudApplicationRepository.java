package com.nearu.nearu;

public interface StudApplicationRepository {
    void save(StudApplication s);
    StudApplication fetch(Integer applicationNo);
    void update(StudApplication u);
    void delete(Integer applicationNo);

}
