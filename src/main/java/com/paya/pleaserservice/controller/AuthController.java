package com.paya.pleaserservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(maxAge = 3600)
public class AuthController {
    //This API is used for authentication using Keycloak
    @Value("${spring.security.oauth2.client.provider.keycloak.token-uri}")
    private String tokenUri;
    @Value("${spring.security.oauth2.client.registration.keycloak.redirect-uri}")
    private String redirectUri;
    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String clientID;
    @Value("${spring.security.oauth2.client.registration.keycloak.client-secret}")
    private String clientSecret;


    @GetMapping("/oauth2/callback")
    public ResponseEntity<?> handleOAuth2Callback(@RequestParam String code) {
        // This method is used to generate access token for Keycloak login using code parameter
        try {
            RestTemplate restTemplate = new RestTemplate();
            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/x-www-form-urlencoded");
            // create request body
            String body = "grant_type=authorization_code" +
                    "&code=" + code +
                    "&redirect_uri=" + redirectUri +
                    "&client_id=" + clientID +
                    "&client_secret=" + clientSecret;
            // Send request to Keycloak
            HttpEntity<String> request = new HttpEntity<>(body, headers);
            return restTemplate.exchange(tokenUri, HttpMethod.POST, request, String.class);
        } catch (Exception exception) {
            ResponseStatusException errorResponse = new ResponseStatusException(HttpStatus.BAD_REQUEST, "کد ارسال شده نامعتبر است");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

}
