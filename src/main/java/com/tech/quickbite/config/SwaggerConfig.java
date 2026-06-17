package com.tech.quickbite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration 
public class SwaggerConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {

	    final String securitySchemeName = "bearerAuth";

	    return new OpenAPI()
	            .info(
	                    new Info()
	                            .title("QuickBite API")
	                            .version("1.0")
	                            .description(
	                                    "Food Delivery Backend System"))
	            .addSecurityItem(
	                    new SecurityRequirement()
	                            .addList(
	                                    securitySchemeName))
	            .components(
	                    new Components()
	                            .addSecuritySchemes(
	                                    securitySchemeName,

	                                    new SecurityScheme()
	                                            .name(
	                                                    securitySchemeName)
	                                            .type(
	                                                    SecurityScheme.Type.HTTP)
	                                            .scheme(
	                                                    "bearer")
	                                            .bearerFormat(
	                                                    "JWT")));
	}

}
