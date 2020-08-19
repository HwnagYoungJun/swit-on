package com.ssafy.switon.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket postsApi() {

//		List<Parameter> global = new ArrayList<>();
//		global.add(new ParameterBuilder().
//			name("Authorization").
//			description("Access Token").
//			parameterType("header").
//			required(false).
//			modelRef(new ModelRef("string")).build());
		
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("SwitOn")
				.apiInfo(apiInfo())
//				.globalOperationParameters(global)
				.securityContexts(Lists.newArrayList(securityContext()))
				.securitySchemes(Lists.newArrayList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.switon.controller"))
				.paths(PathSelectors.ant("/**"))
				.build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("SwitOn API")
				.description("SwitOn API Reference for D101 Developer")
				.termsOfServiceUrl("https://edu.ssafy.com")
				.license("SwitOn License")
				.licenseUrl("http://i3d101.p.ssafy.io/").version("1.0").build();
	}
	
	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authentication", "header");
	}
	
	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.any())
				.build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
	}

}
