package com.vodafone.projectoverview.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRequest {

    private String name;
    private String alias;
    private Integer points;
    private Integer weight;
    private String description;
    private Boolean active;
}
