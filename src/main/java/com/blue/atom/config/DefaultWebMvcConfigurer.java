package com.blue.atom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DefaultWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(authenticationInterceptor);
        // 拦截路径
        interceptorRegistration.addPathPatterns("/**");
        // 不拦截路径
        List<String> excludePath = new ArrayList<String>();
        // 开放knife4j
        excludePath.add("/doc.html");
        excludePath.add("/service-worker.js");
        excludePath.add("/swagger-resources");
        interceptorRegistration.excludePathPatterns(excludePath);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 部署到管理后台的配置
        registry.addViewController("/").setViewName("/index.html");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                .maxAge(3600);
    }
}
