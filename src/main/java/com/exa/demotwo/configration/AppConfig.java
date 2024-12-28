package com.exa.demotwo.configration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.exa.demotwo" })
@PropertySource("classpath:application.properties")
public class AppConfig {
   

  

   

}