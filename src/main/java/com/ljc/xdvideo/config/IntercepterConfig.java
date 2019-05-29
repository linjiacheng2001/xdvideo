package com.ljc.xdvideo.config;

import com.ljc.xdvideo.interceoter.LoginIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 功能描述:
 * 拦截器配置
 *
 * @author linjiacheng2001
 * @date 2019-03-01 15:32
 */
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginIntercepter()).addPathPatterns("/user/api/v1/*/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
