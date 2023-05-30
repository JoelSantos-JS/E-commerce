package com.ecommerce.ECommerce.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

/**
 * Configuration of the security on endpoints.
 */
@Configuration
public class WebSecurityConfig {

    @Autowired
    private JwtRequestService jwtTokenProvider;

    /**
     * Filter chain to configure security.
     * 
     * @param http The security object.
     * @return The chain built.
     * @throws Exception Thrown on error configuring.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: Proper authentication.
        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwtTokenProvider, AuthorizationFilter.class);
        http.authorizeHttpRequests().requestMatchers("/product", "auth/register", "auth/login", "auth/verify")
                .permitAll().anyRequest()
                .authenticated();
        return http.build();
    }

}