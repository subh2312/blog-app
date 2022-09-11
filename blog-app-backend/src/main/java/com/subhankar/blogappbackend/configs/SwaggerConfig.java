package com.subhankar.blogappbackend.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {
    public static final String AUTHORIZATION_HEADER="Authorization";
    private ApiKey apiKey(){
        return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
    }
    private List<SecurityContext> securityContexts(){
        return Arrays.asList(SecurityContext.builder().securityReferences(securityReferences()).build());
    }
    private List<SecurityReference> securityReferences(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
        return Arrays.asList(new SecurityReference("scope",new AuthorizationScope[]{authorizationScope}));
    }
    @Bean
    public Docket apiDocket(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .securityContexts(securityContexts())
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getInfo() {
        return new ApiInfo("Blogging Application",
                "This backend application is developed by Subhankar",
                "1.0","Terms & conditions",new Contact("Subhankar", "subh2312@github.com","subhankar@zkteco.in"),
                "Licence of Api","API Licence url", Collections.emptyList());
    }
}
