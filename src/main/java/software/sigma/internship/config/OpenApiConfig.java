package software.sigma.internship.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI springShopOpenAPI(BuildProperties buildProperties) {
        return new OpenAPI().info(
                new Info()
                        .title("SSU API")
                        .description("Sigma Software University Internship project")
                        .version(buildProperties.getVersion())
        );
    }
}
