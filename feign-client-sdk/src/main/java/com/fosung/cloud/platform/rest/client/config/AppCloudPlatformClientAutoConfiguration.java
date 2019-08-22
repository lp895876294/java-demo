package com.fosung.cloud.platform.rest.client.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Slf4j
@Configuration
@EnableConfigurationProperties({AppCloudPlatformClientProperties.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
@ComponentScan(basePackages = "com.fosung.cloud.platform.rest.client.api")
public class AppCloudPlatformClientAutoConfiguration {



}
