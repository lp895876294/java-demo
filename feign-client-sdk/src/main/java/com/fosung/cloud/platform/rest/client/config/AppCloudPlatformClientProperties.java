package com.fosung.cloud.platform.rest.client.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.cloud.client")
@Setter
@Getter
public class AppCloudPlatformClientProperties {

    public String url ;

}
