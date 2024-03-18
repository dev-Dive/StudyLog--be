package com.devdive.studylog.config;

import com.devdive.studylog.security.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 인증 수단 수정
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // filter 설정
                .addFilterBefore(new JwtAuthorizationFilter(jwtManager), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)

                // 인가 설정
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/api/v1/pill/")
                                .access(new WebExpressionAuthorizationManager("hasRole('ADMIN') or hasRole('MEMBER')"))
                                .requestMatchers("/api/v1/member/")
                                .access(new WebExpressionAuthorizationManager("hasRole('ADMIN') or hasRole('MEMBER')"))
                                .anyRequest().permitAll()
                )

                // 예외처리 설정
                .exceptionHandling(ex ->
                        ex.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                )
                .build();
    }

}
