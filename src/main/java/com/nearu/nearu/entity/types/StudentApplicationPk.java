package com.nearu.nearu.entity.types;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentApplicationPk {

    private Integer userNo;
    private Integer applicationNo;

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof StudentApplicationPk))
            return false;
        else
            return userNo == ((StudentApplicationPk)obj).userNo && applicationNo == ((StudentApplicationPk)obj).applicationNo;
    }
}
