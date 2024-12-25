package com.exa.demotwo.configration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.exa.demotwo" })
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Autowired
    private Environment env;

    @Bean
    ViewResolver viewResolver() {
        InternalResourceViewResolver ivr = new InternalResourceViewResolver();
        ivr.setPrefix(env.getProperty("spring.mvc.view.prefix"));
        ivr.setSuffix(env.getProperty("spring.mvc.view.suffix"));
        return ivr;
    }

  

   

}