package com.nearu.nearu.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Application {
    private Integer applicationNo;
    private Boolean status;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private String location;
    private String conditions;
    private LocalDateTime dDay;
    private Integer adminNo;

    public Application() {
    }

    public Application(LocalDateTime dueDate, String location, String conditions, LocalDateTime dDay, Integer adminNo) {
        this.dueDate = dueDate;
        this.location = location;
        this.conditions = conditions;
        this.dDay = dDay;
        this.adminNo = adminNo;
    }

}
