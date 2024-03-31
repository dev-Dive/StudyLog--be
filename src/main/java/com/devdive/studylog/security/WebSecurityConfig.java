package com.devdive.studylog.security;

import com.devdive.studylog.service.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    // Todo: gc 대상에 따라 Spring Bean 주입 방법 선택
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            TokenAuthenticationFilter tokenAuthenticationFilter
    ) throws Exception {
        return http
                // 인증 수단 수정
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // filter 설정
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )

                // 인가 설정
//                .authorizeHttpRequests(authorize ->
//                        authorize.requestMatchers("/api/v1/pill/")
//                                .access(new WebExpressionAuthorizationManager("hasRole('ADMIN') or hasRole('MEMBER')"))
//                                .requestMatchers("/api/v1/member/")
//                                .access(new WebExpressionAuthorizationManager("hasRole('ADMIN') or hasRole('MEMBER')"))
//                                .anyRequest().permitAll()
//                )

                // 예외처리 설정
//                .exceptionHandling(ex ->
//                        ex.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
//                )
                .build();
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter(
            JwtProvider jwtProvider,
            AuthenticationManager authenticationManager
    ) {
        TokenAuthenticationFilter filter = new TokenAuthenticationFilter("/api/v1/auth/email/sign*", authenticationManager);
        filter.setAuthenticationSuccessHandler(new TokenAuthenticationSuccessHandler(jwtProvider));
        return filter;
    }

    @Bean
    public AuthenticationManager authenticationManager(TokenRepository tokenRepository) {
        return new ProviderManager(new TokenAuthenticationProvider(tokenRepository));
    }

    @Bean
    public TokenRepository tokenRepository() {
        return new InMemoryTokenRepository(new TokenProvider());
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withIssuerLocation()
                .jwsAlgorithm()
                .build();
    }

}
