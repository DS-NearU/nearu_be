package com.nearu.nearu.services;

import com.nearu.nearu.entity.Application;
import com.nearu.nearu.entity.StudApplication;
import com.nearu.nearu.repository.ApplicationRepository;
import com.nearu.nearu.repository.StudApplicationRepository;
import com.nearu.nearu.request.ApplicationDto;
import com.nearu.nearu.request.StudApplicationDto;

import java.time.LocalDateTime;
import java.util.*;

public class ApplicationService{
    private ApplicationRepository applicationRepository;
    private StudApplicationRepository studApplicationRepository;

    public void saveApplication(ApplicationDto a){
        Application app = new Application();
        app.setCreatedAt(LocalDateTime.now());
        app.setAdminNo(a.getAdminNo());
        app.setConditions(a.getConditions());
        app.setDDay(a.getDDay());
        app.setLocation(a.getLocation());
        app.setDueDate(a.getDueDate());
        app.setStatus(a.getStatus());
        applicationRepository.save(app);
    }

    public void saveStudApplication(StudApplicationDto stu){
        StudApplication stud = new StudApplication();
        stud.setUserNo(stu.getUserNo());
        stud.setApplicationNo(stu.getApplicationNo());
        stud.setIsConfirmed(false);
        studApplicationRepository.save(stud);
    }
}
