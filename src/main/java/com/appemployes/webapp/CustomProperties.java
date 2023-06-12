package com.appemployes.webapp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties( prefix ="com.appemployes.webapp")
public class CustomProperties {

    private String apiUrl;
}
