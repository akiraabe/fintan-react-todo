package com.example.demo;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsPreFlightConfiguration {

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(CorsConfiguration.ALL);
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.addAllowedMethod(CorsConfiguration.ALL); // 細かく設定可
        //source.registerCorsConfiguration("/oauth/token", config); // OAuth EP
        source.registerCorsConfiguration("/api/**", config); // 個別設定
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));

        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
