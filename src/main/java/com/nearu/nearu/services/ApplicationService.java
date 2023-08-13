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
import org.apache.http.HttpException;
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
    private final UserRepository userRepository;

    @Transactional
    public void saveApplication(ApplicationDto a) throws HttpException {
        Application app = new Application();
        Integer adminNo = userRepository.findByUserId(a.getUserId()).getUserNo();
        app.setCreatedAt(LocalDateTime.now());
        app.setAdminNo(adminNo);
        app.setConditions(a.getConditions());
        app.setDDay(a.getDDay());
        app.setDurationHours(a.getDurationHours());
        app.setLocation(a.getLocation());
        if (LocalDateTime.now().plusHours(24).isBefore(app.getDDay())) {
            app.setDueDate(a.getDDay().minusHours(24));
        }
        else {
            throw new HttpException("Your appointment date has to be later than 24 hours from now.");
        }
        app.setStatus(false);
        applicationRepository.save(app);
    }

    @Transactional
    public void saveStudApplication(StudApplicationDto stu){
        Integer userNo = userRepository.findByUserId(stu.getUserId()).getUserNo();
        if(studApplicationRepository.findByApplicationNoAndUserNo(stu.getApplicationNo(), userNo)!=null){
            return;
        }
        StudApplication stud = new StudApplication();
        stud.setUserNo(userNo);
        stud.setApplicationNo(stu.getApplicationNo());
        stud.setIsConfirmed(false);
        studApplicationRepository.save(stud);
    }

    @Transactional
    public void updateApplication(ApplicationDto a) throws HttpException {
        Application app = applicationRepository.findByApplicationNo(a.getApplicationNo());
        if (!app.getStatus()) {
            app.setDDay(a.getDDay());
            app.setConditions(a.getConditions());
            app.setLocation(a.getLocation());
            app.setDurationHours(a.getDurationHours());
            if (LocalDateTime.now().plusHours(24).isBefore(app.getDDay())) {
                app.setDueDate(a.getDDay().minusHours(24));
            }
            else {
                throw new HttpException("Your appointment date has to be later than 24 hours from now.");
            }
            applicationRepository.save(app);
        }
        else {
            throw new HttpException("The reservation is already matched with a volunteer.");
        }

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
        Application app = applicationRepository.findByApplicationNo(applicationNo);
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

     public ArrayList<Application> fetchAllByAdmin(String userId){
        Integer adminNo = userRepository.findByUserId(userId).getUserNo();
        return applicationRepository.findAllByAdminNo(adminNo);
     }

     public Application fetch(Integer applicationNo){
        return applicationRepository.findByApplicationNo(applicationNo);
     }

     public StudApplication fetchApplicant(Integer applicationNo, Integer userNo){
        return studApplicationRepository.findByApplicationNoAndUserNo(applicationNo, userNo);
     }

     public void selectApplicant(Integer applicationNo, Integer userNo){
        Application a = applicationRepository.findByApplicationNo(applicationNo);
        a.setStatus(false);
        applicationRepository.save(a);
        StudApplication fetch = studApplicationRepository.findByApplicationNoAndUserNo(applicationNo, userNo);
        fetch.setIsConfirmed(true);
        studApplicationRepository.save(fetch);
     }
}
