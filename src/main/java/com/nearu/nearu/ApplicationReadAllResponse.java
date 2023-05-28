package com.nearu.nearu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ApplicationReadAllResponse {
    private String name;
    private Application app;
    private Integer numberApplicants;
}
