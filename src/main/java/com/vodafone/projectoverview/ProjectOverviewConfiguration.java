package com.vodafone.projectoverview;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = {
                "com.vodafone.projectoverview.presentation",
                "com.vodafone.projectoverview.service",
                "com.vodafone.projectoverview.repository"
        }
)
public class ProjectOverviewConfiguration {

    public static final String LOGGER_NAME = "com.vodafone.projectoverview";

    public ProjectOverviewConfiguration() {
        super();
    }
}
