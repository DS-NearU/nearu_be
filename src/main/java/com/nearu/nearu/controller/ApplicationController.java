package com.nearu.nearu.controller;

import com.nearu.nearu.OriginObject;
import com.nearu.nearu.SessionRequest;
import com.nearu.nearu.request.ApplicationDto;
import com.nearu.nearu.services.ApplicationService;
import com.nearu.nearu.entity.Application;
import com.nearu.nearu.entity.StudApplication;
import com.nearu.nearu.entity.UserInfo;
import com.nearu.nearu.request.ApplicationReadAllResponse;
import com.nearu.nearu.request.ApplicationReadResponse;
import com.nearu.nearu.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class ApplicationController extends OriginObject {
    private final ApplicationService applicationService;
    private final UserService userService;


    @PostMapping("/application")
    public void upload (SessionRequest request) {
        ApplicationDto map = map(request.getParam(), ApplicationDto.class);
        applicationService.saveApplication(map);
    }

    @PutMapping("/application")
    public void edit (SessionRequest request) {
        ApplicationDto map = map(request.getParam(), ApplicationDto.class);
        applicationService.updateApplication(map);
    }

    @DeleteMapping("/application")
    public void delete(SessionRequest request) {
        ApplicationDto map = map(request.getParam(), ApplicationDto.class);
        applicationService.deleteApplication(map.getApplicationNo());
    }

    @GetMapping("/applicants")
    public ApplicationReadResponse read(SessionRequest request){
        ApplicationDto map = map(request.getParam(), ApplicationDto.class);
        return ApplicationService.fetchApplication(map.getApplicationNo());
    }

    public ArrayList<ApplicationReadAllResponse> readAll() {
        ArrayList<ApplicationReadAllResponse> responses = new ArrayList<>();
        ArrayList<Application> applications = applicationService.fetchAll();
        for (int i = 0; i < applications.size(); i++) {
            ApplicationReadAllResponse response = new ApplicationReadAllResponse();
            Integer applicationNo = applications.get(i).getApplicationNo();
            Integer adminNo = applicationService.fetch(applicationNo).getAdminNo();
            response.setName(userInfoService.fetch(adminNo).getName());
            response.setApp(applicationService.fetch(applicationNo));
            ArrayList<StudApplication> applicants = studApplicationService.fetch(applicationNo);
            response.setNumberApplicants(applicants.size());
            responses.add(response);
        }
        return responses;
    }

    public void register (Integer userNo, Integer applicationNo) {
        if (!applicationService.fetch(applicationNo).getStatus()) {
            return;
        }
        StudApplication app = new StudApplication();
        app.setUserNo(userNo);
        app.setApplicationNo(applicationNo);
        app.setIsConfirmed(false);
        studApplicationService.save(app);
    }

    public void cancel (Integer userNo, Integer applicationNo) {
        Application a = applicationService.fetch(applicationNo);
        if (LocalDateTime.now().isBefore(a.getDDay().minusDays(1L))) {
            studApplicationService.delete(applicationNo, userNo);
            if (!a.getStatus()) {
                a.setStatus(true);
            }
        }
    }

    public ArrayList<Application> viewMyApplications (Integer userNo) {
        return (ArrayList<Application>) applicationService.fetchAllByAdmin(userNo);
    }

    public void selectApplicant (Integer userNo, Integer applicationNo) {
        Application a = applicationService.fetch(applicationNo);
        a.setStatus(false);
        applicationService.update(a);
        StudApplication fetch = studApplicationService.fetch(applicationNo, userNo);
        fetch.setIsConfirmed(true);
        studApplicationService.update(fetch);
    }






}
