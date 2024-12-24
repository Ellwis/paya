//package com.paya.pleaserservice.security;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Value("${keycloak.server}")
//    private String keycloakServer;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .requestMatchers("/oauth2/callback").permitAll()
//                .requestMatchers("/**").permitAll()
//                .requestMatchers("pleaser-services/**").permitAll()
//                .requestMatchers("/oauth2/authorization/keycloak/**").permitAll()
//                .requestMatchers("/oauth2/callback/").permitAll()
//                .requestMatchers("/oauth2/authorization/keycloak").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .oauth2Login()
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
//
//        return http.build();
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        String jwkSetUri = keycloakServer + "/auth/realms/TebyanWeb/protocol/openid-connect/certs";
//        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
//    }
//}