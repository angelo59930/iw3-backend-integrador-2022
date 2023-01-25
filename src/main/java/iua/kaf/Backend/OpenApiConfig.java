package iua.kaf.Backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
  @Bean
  public OpenAPI openApi(){
    return new OpenAPI()
      .info(new Info()
        .title("Backend API")
        .version("1.0.0")
        .description("Backend API for IW3 project")
        .contact(new Contact()
          .name("KAF")
          .url("https://github.com/angelo59930/iw3-backend-integrador-2022")
        )
        .termsOfService("TOC")
        .license(new License().name("License").url("#"))
        );
      }
}