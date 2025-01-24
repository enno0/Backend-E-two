package com.exa.demotwo.configration;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.lang.NonNull;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * The MainApp class is responsible for initializing the web application.
 * It implements the WebApplicationInitializer interface to configure the
 * servlet context programmatically.
 */
public class MainApp implements WebApplicationInitializer {

    /**
     * This method is called on application startup to configure the servlet context.
     *
     * @param servletContext the ServletContext to be configured
     * @throws ServletException if an error occurs during servlet initialization
     */
    @Override
    public void onStartup(@NonNull ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

        context.register(AppConfig.class);
        context.setServletContext(servletContext);
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
        context.close();
    }
}