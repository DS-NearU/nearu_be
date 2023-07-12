package com.nearu.nearu.request;

import com.nearu.nearu.entity.Qa;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class QaCountsResponse {
    private Qa question;
    private Integer countComments;
    private String name;
}
