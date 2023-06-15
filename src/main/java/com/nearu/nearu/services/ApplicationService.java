package com.nearu.nearu.services;

import com.amazonaws.services.fms.model.App;
import com.nearu.nearu.entity.Application;
import com.nearu.nearu.entity.StudApplication;
import com.nearu.nearu.entity.UserInfo;
import com.nearu.nearu.repository.ApplicationRepository;
import com.nearu.nearu.repository.StudApplicationRepository;
import com.nearu.nearu.repository.UserInfoRepository;
import com.nearu.nearu.repository.UserRepository;
import com.nearu.nearu.request.ApplicationDto;
import com.nearu.nearu.request.ApplicationReadAllResponse;
import com.nearu.nearu.request.ApplicationReadResponse;
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
    private final UserInfoRepository userInfoRepository;

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
        if(studApplicationRepository.findByApplicationNoAndUserNo(stu.getApplicationNo(), stu.getUserNo()){
            return;
        }
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

    public ArrayList<ApplicationReadAllResponse> fetchAllApplications(){
        ArrayList<Application> arrList = (ArrayList<Application>) applicationRepository.findAll();
        ArrayList<ApplicationReadAllResponse> list = new ArrayList<>();
        for(int i = 0; i<arrList.size(); i++){
            ApplicationReadAllResponse res = new ApplicationReadAllResponse();
            res.setApp(arrList.get(i));
            res.setNumberApplicants(studApplicationRepository.countAllByApplicationNo(arrList.get(i).getApplicationNo()));
            Integer userNo = arrList.get(i).getAdminNo();
            res.setName(userInfoRepository.findByUserNo(userNo).getName());
            list.add(res);
        }
        return list;
    }

    public ApplicationReadResponse fetchApplicants(Integer applicationNo){
        ApplicationReadResponse res = new ApplicationReadResponse();
        Application app = applicationRepository.findByApplicationNo(applicationNo)
        Integer adminNo = app.getAdminNo();
        res.setName(userInfoRepository.findByUserNo(adminNo).getName());
        res.setApp(app);
        ArrayList<UserInfo> applicants = new ArrayList<>();
        ArrayList<StudApplication> studApplications = studApplicationRepository.findAllByApplicationNo(applicationNo);
        for(int i = 0; i<studApplications.size(); i++){
            Integer userNo = studApplications.get(i).getUserNo();
            UserInfo info = userInfoRepository.findByUserNo(userNo);
            applicants.add(info);
        }
        res.setApplicants(applicants);
        return res;
    }

    @Transactional
    public void deleteApplication(Integer applicationNo){
        studApplicationRepository.deleteAllByApplicationNo(applicationNo);
        applicationRepository.deleteByApplicationNo(applicationNo);
    }

    @Transactional
    public void deleteStudApplication(Integer applicationNo, Integer userNo){
        Application a = applicationRepository.findByApplicationNo(applicationNo);
        if (LocalDateTime.now().isBefore(a.getDDay().minusDays(1L))) {
            studApplicationRepository.deleteByApplicationNoAndUserNo(applicationNo, userNo);
            if (!a.getStatus()) {
                a.setStatus(true);
                applicationRepository.save(a);
            }
        }
     }
}
