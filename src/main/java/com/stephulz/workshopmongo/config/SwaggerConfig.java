package com.stephulz.workshopmongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	  @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.any())              
	          .paths(PathSelectors.any())                          
	          .build()
	          .apiInfo(apiInfo());	          
	    }
	  
	  private ApiInfo apiInfo() {
		  return new ApiInfoBuilder()
				  .title("Workshop MongoDB")
				  .description("REST API for the MongoDB Workshop")
				  .contact(new Contact("Stephulz","https://github.com/Stephulz","stefansanches9@gmail.com"))				
				  .build();
	  }

}
