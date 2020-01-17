package ru.suchkov.votesystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

import static com.google.common.base.Predicates.not;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfo(
                                "Restourant vote system API",
                                "API for graduation project TopJava",
                                "1.0",
                                "https://github.com/SuchkovDenis/votes",
                                new Contact(
                                        "Suchkov Denis",
                                        "https://github.com/suchkovdenis",
                                        "ny1828@yandex.ru"
                                ),
                                "GPL 3.0",
                                "https://www.gnu.org/licenses/gpl-3.0.en.html",
                                Collections.emptySet()
                        )
                )
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(not(regex("/error.*")))
                .build()
                .securitySchemes(securitySchemes());
    }

    private static List<? extends SecurityScheme> securitySchemes() {
        return List.of(new BasicAuth("Basic"));
    }
}