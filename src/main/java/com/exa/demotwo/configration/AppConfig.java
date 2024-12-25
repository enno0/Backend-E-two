package com.exa.demotwo.configration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "banquemisr.challenege05.taskmangmentsystem" })
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

    @SuppressWarnings("null")
    @Bean
    DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean
    SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

}