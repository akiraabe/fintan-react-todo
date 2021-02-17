package com.example.demo;

import com.example.demo.auth.service.UserDetailsServiceImpl;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("UserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/signup").permitAll()
                .anyRequest().authenticated();

        // ログインパラメーター設定
        JsonAuthenticationFilter authenticationFilter = new JsonAuthenticationFilter(authenticationManager());
        authenticationFilter.setUsernameParameter("userName");
        authenticationFilter.setPasswordParameter("password");
        authenticationFilter.setAuthenticationSuccessHandler((req, res, auth) -> res.setStatus(HttpServletResponse.SC_OK));
        authenticationFilter.setAuthenticationFailureHandler((req, res, auth) -> res.setStatus(HttpServletResponse.SC_UNAUTHORIZED));

        //Formログインのフィルターを置き換える
        http.addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // ログイン処理
        http.formLogin().loginProcessingUrl("/api/login")
                .loginPage("/api/login")
                .failureUrl("/api/login")
                .usernameParameter("userName")
                .passwordParameter("password")
                .defaultSuccessUrl("/api/board", true);

        // logout
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
                .logoutUrl("/api/logout")
                .logoutSuccessUrl("/api/login")
                .invalidateHttpSession(true);
        ;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
