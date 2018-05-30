package com.github.bruce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final static String CLIENT_ID = "Bruce";
    private final static String CLIENT_SECRET = "Swagger";
    private final static String AUTH_SERVER = "http://localhost:8080/spring-security-oauth-server";

    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.useDefaultResponseMessages(false);
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(500).message("500 error message").responseModel(new ModelRef("Error"))
                .build());
        responseMessageList.add(new ResponseMessageBuilder().code(403).message("Forbidden")
                .build());
        responseMessageList.add(new ResponseMessageBuilder().code(400).message("Bad request !!!")
                .build());
        docket.globalResponseMessage(RequestMethod.POST, responseMessageList);

        docket.select()
        .apis(RequestHandlerSelectors.basePackage("com.github.bruce.controller"))
        .paths(PathSelectors.ant("/swagger/*"))
        .build()
        .apiInfo(apiInfo());
//        .securitySchemes(Arrays.asList(securityScheme()))
//        .securityContexts(Arrays.asList(securityContext()));

        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "My REST API",
                "Some custom description of API.",
                "API TOS",
                "Terms of service",
                new Contact("Bruce", "www.example.com", "bruce@company.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

    @Bean
    public SecurityConfiguration security() {
        return new SecurityConfiguration(CLIENT_ID, CLIENT_SECRET, null, null, null, null, null, "|");
    }

    private SecurityScheme securityScheme() {
        GrantType grantType = new AuthorizationCodeGrantBuilder()
                .tokenEndpoint(new TokenEndpoint(AUTH_SERVER + "/token", "oauthtoken"))
                .tokenRequestEndpoint(
                        new TokenRequestEndpoint(AUTH_SERVER + "/authorize", CLIENT_ID, CLIENT_ID))
                .build();

        SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
                .grantTypes(Arrays.asList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
        return oauth;
    }

    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = {
                new AuthorizationScope("read", "for read operations"),
                new AuthorizationScope("write", "for write operations"),
                new AuthorizationScope("swagger", "Access foo API") };
        return scopes;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(new SecurityReference("spring_oauth", scopes())))
                .forPaths(PathSelectors.regex("/swagger/*"))
                .build();
    }
}
