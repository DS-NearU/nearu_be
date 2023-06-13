package com.nearu.nearu.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_no")
    private Integer applicationNo;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "due_date")
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private String location;
    private String conditions;
    private LocalDateTime dDay;

    @ManyToOne
    @Column(name = "admin_no")
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
