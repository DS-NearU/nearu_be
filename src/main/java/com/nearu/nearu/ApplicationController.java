package com.nearu.nearu;

import org.joda.time.LocalTime;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ApplicationController {
    private final ApplicationService applicationService = new ApplicationService();

    private final StudApplicationService  studApplicationService = new StudApplicationService();

    private final UserInfoService userInfoService = new UserInfoService();


    public void upload (LocalDateTime dueDate, String location, String conditions, LocalDateTime dDay, Integer adminNo) {
        Application app = new Application (dueDate, location, conditions, dDay, adminNo);
        app.setStatus(true); // true is open
        app.setCreatedAt(LocalDateTime.now());
        applicationService.save(app);
    }

    public void edit (Integer applicationNo, LocalDateTime dueDate, String location, String conditions, LocalDateTime dDay) {
        Application app = applicationService.fetch(applicationNo);
        app.setDueDate(dueDate);
        app.setLocation(location);
        app.setConditions(conditions);
        app.setDDay(dDay);
        applicationService.update(app);
    }

    public void delete(Integer applicationNo) {
        studApplicationService.deleteAllByApplication(applicationNo);
        applicationService.delete(applicationNo);
    }

    public ApplicationReadResponse read(Integer applicationNo){
        ApplicationReadResponse response = new ApplicationReadResponse();

        Integer adminNo = applicationService.fetch(applicationNo).getAdminNo();
        response.setName(userInfoService.fetch(adminNo).getName());
        response.setApp(applicationService.fetch(applicationNo));
        ArrayList<StudApplication> applicants = studApplicationService.fetch(applicationNo);
        ArrayList<Integer> userNumbers = new ArrayList<>();
        for(StudApplication a : applicants) {
            userNumbers.add(a.getUserNo());
        }
        ArrayList<UserInfo> applicantsInfo = userInfoService.fetch(userNumbers);
        response.setApplicants(applicantsInfo);
        return response;
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
