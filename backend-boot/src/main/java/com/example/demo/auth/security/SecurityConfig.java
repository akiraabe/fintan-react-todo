package com.example.demo.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        JsonAuthenticationFilter jsonAuthenticationFilter = new JsonAuthenticationFilter(authenticationManager());
        jsonAuthenticationFilter.setUsernameParameter("userName");
        jsonAuthenticationFilter.setPasswordParameter("password");
        // @formatter:off
        http
            // JSON用フィルターへの差し替え
            .addFilterAt(jsonAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            // authorize
            .authorizeRequests()
                .mvcMatchers("/api/login", "/api/signup")
                    .permitAll()
                .anyRequest()
                    .authenticated()
            .and()
            // Exception
            .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())
            .and()
            // Login
            .formLogin()
                .loginProcessingUrl("/api/login").permitAll()
                    .usernameParameter("userName")
                    .passwordParameter("password")
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())
            .and()
            // Logout
            .logout()
                .logoutUrl("/api/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler())
            .and()
            // CSRF
            .csrf()
                .disable()
//                .csrfTokenRepository(new CookieCsrfTokenRepository())
            // Session
            .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
            ;
        // @formatter:on
    }



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
                                @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                                PasswordEncoder passwordEncoder) throws Exception {
        auth.eraseCredentials(true)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    AuthenticationEntryPoint authenticationEntryPoint() {
        return new SimpleAuthenticationEntryPoint();
    }

    AccessDeniedHandler accessDeniedHandler() {
        return new SimpleAccessDeniedHandler();
    }

    AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleAuthenticationSuccessHandler();
    }

    AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleAuthenticationFailureHandler();
    }

    LogoutSuccessHandler logoutSuccessHandler() {
        return new HttpStatusReturningLogoutSuccessHandler();
    }
}

