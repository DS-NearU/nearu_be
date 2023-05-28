package com.nearu.nearu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudApplication {
    private Integer userNo;
    private Integer applicationNo;
    private Boolean isConfirmed;

    public StudApplication() {

    }

    public StudApplication(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }
}
