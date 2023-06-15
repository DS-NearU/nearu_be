package com.nearu.nearu.services;

import com.nearu.nearu.entity.Application;
import com.nearu.nearu.entity.StudApplication;
import com.nearu.nearu.repository.ApplicationRepository;
import com.nearu.nearu.repository.StudApplicationRepository;
import com.nearu.nearu.request.ApplicationDto;
import com.nearu.nearu.request.StudApplicationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ApplicationService{
    private final ApplicationRepository applicationRepository;
    private final StudApplicationRepository studApplicationRepository;

    @Transactional
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

    @Transactional
    public void saveStudApplication(StudApplicationDto stu){
        StudApplication stud = new StudApplication();
        stud.setUserNo(stu.getUserNo());
        stud.setApplicationNo(stu.getApplicationNo());
        stud.setIsConfirmed(false);
        studApplicationRepository.save(stud);
    }

    @Transactional
    public void updateApplication(ApplicationDto a){
        Application app = applicationRepository.findByApplicationNo(a.getApplicationNo());
        app.setDDay(a.getDDay());
        app.setConditions(a.getConditions());
        app.setStatus(a.getStatus());
        app.setLocation(a.getLocation());
        app.setDueDate(a.getDueDate());
        applicationRepository.save(app);
    }

    @Transactional
    public void updateStudApplication(StudApplicationDto stu){
        StudApplication stud = studApplicationRepository.findByApplicationNoAndUserNo(stu.getApplicationNo(), stu.getUserNo());
        stud.setIsConfirmed(stu.getIsConfirmed());
        studApplicationRepository.save(stud);
    }

    public Application fetchApplication(Integer applicationNo){
        return applicationRepository.findByApplicationNo(applicationNo);
    }

    public StudApplication fetchStudApplication(Integer applicationNo, Integer userNo){
        return studApplicationRepository.findByApplicationNoAndUserNo(applicationNo, userNo);
    }

    @Transactional
    public void deleteApplication(Integer applicationNo){
        studApplicationRepository.deleteAllByApplicationNo(applicationNo);
        applicationRepository.deleteByApplicationNo(applicationNo);
    }

    @Transactional
    public void deleteStudApplication(Integer applicationNo, Integer userNo){
        studApplicationRepository.deleteByApplicationNoAndUserNo(applicationNo, userNo);
     }
}
