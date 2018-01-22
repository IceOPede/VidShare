package controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/home").setViewName("index");


        registry.addViewController("/topVideos").setViewName("topVideos");
        registry.addViewController("/uploadSite").setViewName("uploadSite");

        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/login").setViewName("login");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//        registry.addResourceHandler("/Rcss/**").addResourceLocations("/static/css");
//        registry.addResourceHandler("/Rfont/**").addResourceLocations("/static/fonts/roboto");
//        registry.addResourceHandler("/Rjs/**").addResourceLocations("/static/js");
//        registry.addResourceHandler("/Rscript/**").addResourceLocations("/static/Scripts");
//        registry.addResourceHandler("/Rvideos/**").addResourceLocations("/static/Videos");
//    }

}
