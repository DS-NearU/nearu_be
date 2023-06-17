package com.nearu.nearu.controller;

import com.nearu.nearu.OriginObject;
import com.nearu.nearu.SessionRequest;
import com.nearu.nearu.request.ApplicationDto;
import com.nearu.nearu.request.StudApplicationDto;
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
    public ApplicationReadResponse readApplicants(SessionRequest request){
        ApplicationDto map = map(request.getParam(), ApplicationDto.class);
        return applicationService.fetchApplicants(map.getApplicationNo());
    }

    @GetMapping("/applications")
    public ArrayList<ApplicationReadAllResponse> readAll() {
        return applicationService.fetchAllApplications();
    }

    @PostMapping("/register")
    public void register (SessionRequest request) {
        StudApplicationDto map = map(request.getParam(), StudApplicationDto.class);
        applicationService.saveStudApplication(map);
    }

    @DeleteMapping("/cancel")
    public void cancel (SessionRequest request) {
        StudApplicationDto map = map(request.getParam(), StudApplicationDto.class);
        applicationService.deleteStudApplication(map.getApplicationNo(), map.getUserNo());
    }

    @GetMapping("/my-applications")
    public ArrayList<Application> viewMyApplications (SessionRequest request) {
        return applicationService.fetchAllByAdmin(request.getSession().getUserNo());
    }

    @PutMapping("/select-applicant")
    public void selectApplicant (SessionRequest request) {
        StudApplicationDto map = map(request.getParam(), StudApplicationDto.class);
        applicationService.selectApplicant(map.getApplicationNo(),map.getUserNo());
    }






}
