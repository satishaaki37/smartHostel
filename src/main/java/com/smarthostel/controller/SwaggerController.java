package com.smarthostel.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration   //project settings
@EnableSwagger2  //activate swagger file in the background
public class SwaggerController
{
        @Bean  //from the start of project running to the end
        public Docket libraryApi()   //pluggable
        {  
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .groupName("SmartHostel-API")
                    .select().apis(RequestHandlerSelectors.basePackage("com.smarthostel.controller"))  //whose methods do you want to add
                    .build();
 
        }
        private ApiInfo apiInfo()
        {  // used this object to store the meta data
            return new ApiInfoBuilder().title("Smart Hostel API")
                    .description("Smart Hostel Management System Swagger")
                    .build();
        }
}