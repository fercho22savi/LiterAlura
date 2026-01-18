package LiterAlura.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ScreenMatch - Catálogo de Libros")
                        .version("1.0")
                        .description("""
                                API REST para:
                                - Catálogo de libros
                                - Autores
                                - Persistencia en PostgreSQL
                                - Consumo de Gutendex API
                                - Búsquedas avanzadas
                                """));
    }
}
