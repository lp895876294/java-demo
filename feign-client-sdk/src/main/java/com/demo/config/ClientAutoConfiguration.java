package com.demo.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Slf4j
@Configuration
@EnableConfigurationProperties({ClientProperties.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
@ComponentScan(basePackages = "com.demo.api")
public class ClientAutoConfiguration {



}
