package com.example.books.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry controllerRegistry) {
        controllerRegistry.addViewController("/").setViewName("Home");
        controllerRegistry.addViewController("/Login");
    }
}
