package com.zzpzaf.restapidemo.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.zzpzaf.restapidemo.ErrorHandling.CustomAccessDeniedHandler;
import com.zzpzaf.restapidemo.ErrorHandling.CustomAuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
public class CustomSecurityConfiguration {


    @Autowired
    private CustomAuthenticationEntryPoint authPoint;
    
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

      
    // @Autowired
    // private CustomRequestHeaderTokenFilter authFilter;

    // private CustomRequestHeaderTokenFilter customFilter() throws Exception {
    //     //CustomRequestHeaderTokenFilter authFilter = new CustomRequestHeaderTokenFilter(authenticationConfiguration.getAuthenticationManager());
    //     this.authFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/signin","GET"));
    //     return this.authFilter;
    // }
    

    @Bean
    @Autowired
    public SecurityFilterChain filterChain1(HttpSecurity http, CustomRequestHeaderTokenFilter authFilter) throws Exception {
        
        authFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/signin","GET"));
        
        http
            .csrf((csrf) -> csrf.disable());

        http
            .formLogin((formLogin) -> formLogin.disable());
            
        http
            .exceptionHandling(handling -> handling.authenticationEntryPoint(authPoint))
                .exceptionHandling(handling -> handling.accessDeniedHandler(new CustomAccessDeniedHandler()))
            .sessionManagement(management -> management
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST, "/auth/signup/**").permitAll()
                .requestMatchers("/users/**").hasRole("ADMIN")
                .requestMatchers("/roles/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/items/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/items/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/items/**").hasAnyRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/categories/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/categories/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/categories/**").hasAnyRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/vendors/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/vendors/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/vendors/**").hasAnyRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/items/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/categories/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/vendors/**").hasAnyRole("ADMIN", "USER")
            )
            ;
        //http.addFilter(customFilter()); 
        http.addFilter(authFilter); 
        return http.build();
    } 
       

}    




