package com.nearu.nearu.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "student_application")
public class StudApplication {

    @Id
    @ManyToOne
    @Column(name = "user_no")
    private Integer userNo;

    @Id
    @ManyToOne
    @Column(name = "application_no")
    private Integer applicationNo;

    @Column(name = "is_confirmed")
    private Boolean isConfirmed;

    public StudApplication() {

    }

    public StudApplication(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }
}
