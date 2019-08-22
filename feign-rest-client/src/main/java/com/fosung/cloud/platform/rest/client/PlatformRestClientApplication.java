package com.fosung.cloud.platform.rest.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PlatformRestClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                new SpringApplicationBuilder(PlatformRestClientApplication.class).run();

    }

}
