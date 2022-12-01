package com.mypack.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket restApi() {
		
		return	new Docket(DocumentationType.SWAGGER_2)
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.mypack.Controller"))
		.paths(PathSelectors.any())
		.build()
		.apiInfo(apiInfo());
		
		
	}
	public ApiInfo apiInfo() {
		Contact contactInfo= new Contact("Ashok IT","www.ashokit.com","ashokitschool@gmail.com");
		return new ApiInfo("Apring Boot Rest Api",
							"This is for Description",
							"V1.0",
							"URL",
							contactInfo,
							"Apache Lacense version 2.0",
							"https://www.apache.org/Lacenses/LACENSE-2.0"
							);
		
	}

}
