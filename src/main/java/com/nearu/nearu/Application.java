package com.nearu.nearu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Application {
    private Integer applicationNo;
    private Boolean status;
    private String dueDate;
    private String createdAt;
    private String location;
    private String conditions;
    private String dDay;
    private Integer adminNo;
}
