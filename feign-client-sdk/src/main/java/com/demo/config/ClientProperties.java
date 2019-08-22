package com.demo.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.client")
@Setter
@Getter
public class ClientProperties {

    public String url ;

}
