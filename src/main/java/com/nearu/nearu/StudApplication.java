package com.nearu.nearu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudApplication {
    private Integer userNo;
    private Integer applicationNo;
    private Boolean status;

    public StudApplication() {

    }

    public StudApplication(Boolean status) {
        this.status = status;
    }
}
