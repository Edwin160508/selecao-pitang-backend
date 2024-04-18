package br.com.edwin.lima.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
   public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("RESTfull API with Java 21 and SpringBoot 3.2.4")
                                .version("v1")
                                .description("Management Users and Cars")
                                .termsOfService("")
                                .license(new License().name("Apache 2.0")
                                        .url(""))

                );
    }
}
