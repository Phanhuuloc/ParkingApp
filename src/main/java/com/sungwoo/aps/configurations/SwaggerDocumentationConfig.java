package com.sungwoo.aps.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author phloc
 */
@EnableSwagger2
@Component
//@Profile({"prod", "dev", "test"})
public class SwaggerDocumentationConfig {
    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(
                "aps.sungwoo.com");
    }

    @Bean
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("APS WebApp API")
                .description("Public API for Sungwoo Mobile APS Web Service.")
                .license("Sungwoo")
                .licenseUrl("")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact("support@ikornsolutions.com")
                .build();
    }

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sungwoo.aps.controllers"))
                .build()
                .directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }
}
