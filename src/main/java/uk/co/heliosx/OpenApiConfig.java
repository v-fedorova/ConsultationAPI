package uk.co.heliosx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("HeliosX: Consultation API")
                .description("Consultation Endpoints")
                .version("1.0-SNAPSHOT"));
    }
}
