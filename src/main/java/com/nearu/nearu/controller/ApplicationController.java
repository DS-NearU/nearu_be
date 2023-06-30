package com.nearu.nearu.controller;

import com.nearu.nearu.OriginObject;
import com.nearu.nearu.SessionRequest;
import com.nearu.nearu.config.flows.SessionMapper;
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

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class ApplicationController extends OriginObject {
    private final ApplicationService applicationService;
    private final UserService userService;

    @SessionMapper
    @Transactional
    @PostMapping("/application")
    public void upload (SessionRequest request) {
        ApplicationDto map = map(request.getParam(), ApplicationDto.class);
        applicationService.saveApplication(map);
    }

    @SessionMapper
    @Transactional
    @PutMapping("/application")
    public void edit (SessionRequest request) {
        ApplicationDto map = map(request.getParam(), ApplicationDto.class);
        applicationService.updateApplication(map);
    }

    @SessionMapper
    @Transactional
    @DeleteMapping("/application")
    public void delete(SessionRequest request) {
        ApplicationDto map = map(request.getParam(), ApplicationDto.class);
        applicationService.deleteApplication(map.getApplicationNo());
    }

    @SessionMapper
    @GetMapping("/applicants")
    public ApplicationReadResponse readApplicants(SessionRequest request){
        ApplicationDto map = map(request.getParam(), ApplicationDto.class);
        return applicationService.fetchApplicants(map.getApplicationNo());
    }

    @SessionMapper
    @GetMapping("/applications")
    public ArrayList<ApplicationReadAllResponse> readAll(SessionRequest request) {
        return applicationService.fetchAllApplications();
    }

    @SessionMapper
    @Transactional
    @PostMapping("/register")
    public void register (SessionRequest request) {
        StudApplicationDto map = map(request.getParam(), StudApplicationDto.class);
        applicationService.saveStudApplication(map);
    }

    @SessionMapper
    @Transactional
    @DeleteMapping("/cancel")
    public void cancel (SessionRequest request) {
        StudApplicationDto map = map(request.getParam(), StudApplicationDto.class);
        applicationService.deleteStudApplication(map.getApplicationNo(), map.getUserNo());
    }

    @SessionMapper
    @GetMapping("/my-applications")
    public ArrayList<Application> viewMyApplications (SessionRequest request) {
        ApplicationDto map = map(request.getParam(), ApplicationDto.class);
        return applicationService.fetchAllByAdmin(map.getUserId());
    }

    @SessionMapper
    @Transactional
    @PutMapping("/select-applicant")
    public void selectApplicant (SessionRequest request) {
        StudApplicationDto map = map(request.getParam(), StudApplicationDto.class);
        applicationService.selectApplicant(map.getApplicationNo(),map.getUserNo());
    }






}
