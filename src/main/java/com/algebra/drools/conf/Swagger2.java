package com.algebra.drools.conf;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author：xusonglin ===============================
 * Created with IDEA.
 * Date：18-11-8
 * Time：上午10:48
 * ================================
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Autowired
    private FebsServerSystemProperties properties;

//    @Bean
//    public Docket createRestApi() {
//        ParameterBuilder tokenBuilder = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        tokenBuilder.name("Authorization")
//        .defaultValue("")
//        .description("令牌")
//        .modelRef(new ModelRef("string"))
//        .parameterType("header")
//        .required(false).build();
//        pars.add(tokenBuilder.build());
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.algebra.aspect"))
//                .paths(PathSelectors.any())
//                .build().globalOperationParameters(pars)  ;
//    }
//
//    @SuppressWarnings("deprecation")
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("个人测试")
//                .description("个人测试用api")
//                .termsOfServiceUrl("")
//                .contact("测试")
//                .version("1.0")
//                .build();
//    }

    @Bean
    public Docket swaggerApi() {
        FesbSwaggerProperties swagger = properties.getSwagger();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(swagger.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo(swagger))
                .securitySchemes(Collections.singletonList(securityScheme(swagger)))
                .securityContexts(Collections.singletonList(securityContext(swagger)));
    }

    private ApiInfo apiInfo(FesbSwaggerProperties swagger) {
        return new ApiInfo(
                swagger.getTitle(),
                swagger.getDescription(),
                swagger.getVersion(),
                null,
                new Contact(swagger.getAuthor(), swagger.getUrl(), swagger.getEmail()),
                swagger.getLicense(), swagger.getLicenseUrl(), Collections.emptyList());
    }

    private SecurityScheme securityScheme(FesbSwaggerProperties swagger) {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(swagger.getGrantUrl());

        return new OAuthBuilder()
                .name(swagger.getName())
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes(swagger)))
                .build();
    }

    private SecurityContext securityContext(FesbSwaggerProperties swagger) {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference(swagger.getName(), scopes(swagger))))
                .forPaths(PathSelectors.any())
                .build();
    }

    private AuthorizationScope[] scopes(FesbSwaggerProperties swagger) {
        return new AuthorizationScope[]{
                new AuthorizationScope(swagger.getScope(), StringUtils.EMPTY)
        };
    }

}
