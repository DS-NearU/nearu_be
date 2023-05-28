package com.nearu.nearu;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ApplicationReadResponse {
    private String name;
    private Application app;
    private ArrayList<UserInfo>  applicants;
}
