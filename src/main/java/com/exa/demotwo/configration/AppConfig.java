package com.exa.demotwo.configration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The AppConfig class is a Spring configuration class that sets up the application context.
 * It enables Spring's Web MVC support, scans for components in the specified package,
 * and loads properties from the application.properties file.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.exa.demotwo"})
@PropertySource("classpath:application.properties")
public class AppConfig {


}