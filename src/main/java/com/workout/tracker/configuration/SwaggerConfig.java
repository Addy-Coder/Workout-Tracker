package com.workout.tracker.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomSwagger(){
        return new OpenAPI().info(new Info()
                .title("Workout Tracker")
                .description("This is the API documentation for Workout Tracker Application"));
    }
}
