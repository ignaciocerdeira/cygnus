package com.atsistemas.cygnus.services.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	/**
	 * Create Swagger Api configuration
	 *
	 * @return Swagger Docket
	 */
	// @Bean
	public Docket userApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("cygnus").apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(PathSelectors.any()).build()
				.pathMapping("/api").genericModelSubstitutes(ResponseEntity.class).useDefaultResponseMessages(false);
	}

	/**
	 * Generate Api Info
	 *
	 * @return Swagger API Info
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("User").description("Alfa Cygni").version("0.1-SNAPSHOT")
				.termsOfServiceUrl("https://es.wikipedia.org/wiki/Deneb").license("Open source licensing")
				.licenseUrl("https://help.github.com/articles/open-source-licensing/").build();
	}
}
