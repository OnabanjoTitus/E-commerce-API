package com.ecommerce.security.config;


import com.ecommerce.web.contollers.util.ApiRoutes;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().
                authorizeRequests()
                .antMatchers(ApiRoutes.CUSTOMERS+"/buyer/***",ApiRoutes.CUSTOMERS+"/seller/***")
                .permitAll()
                .antMatchers("/swagger-ui/","/api/**", "/swagger-ui.html", "/webjars/**", "/v2/**", "/swagger-resources/**").anonymous()
                .anyRequest()
                .authenticated().and()
                .formLogin();
    }


}
