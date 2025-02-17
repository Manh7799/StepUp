package org.example.project_cuoikhoa_javaweb.config;

import org.example.project_cuoikhoa_javaweb.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthorizationConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor()).addPathPatterns("/admin/**", "/users/**");
    }
}
