package com.github.bruce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

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
}
