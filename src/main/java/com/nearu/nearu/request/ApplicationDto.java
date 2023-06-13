package com.nearu.nearu.request;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ApplicationDto {
    private Integer applicationNo;
    private Boolean status;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private String location;
    private String conditions;
    private LocalDateTime dDay;
    private Integer adminNo;
}
