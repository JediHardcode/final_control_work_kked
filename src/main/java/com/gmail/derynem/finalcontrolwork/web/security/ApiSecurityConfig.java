package com.gmail.derynem.finalcontrolwork.web.security;


import com.gmail.derynem.finalcontrolwork.repository.model.enums.UserRoleEnum;
import com.gmail.derynem.finalcontrolwork.web.security.handler.ApiAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder encoder;
    private final UserDetailsService userDetailsService;

    public ApiSecurityConfig(PasswordEncoder encoder,
                             UserDetailsService userDetailsService) {
        this.encoder = encoder;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AccessDeniedHandler apiDeniedAccessHandler() {
        return new ApiAccessDeniedHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/v1/root/**")
                .authorizeRequests()
                .anyRequest()
                .hasAuthority(UserRoleEnum.SUPER_USER.name())
                .and()
                .antMatcher("/api/v1/discounts")
                .authorizeRequests()
                .anyRequest()
                .hasAuthority(UserRoleEnum.CUSTOMER_USER.name())
                .and()
                .httpBasic()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(apiDeniedAccessHandler())
                .and()
                .csrf()
                .disable();
    }
}