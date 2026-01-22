package LiterAlura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfigurationMvc {

    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration config = new CorsConfiguration();

        // 🌐 Orígenes permitidos
        config.setAllowedOrigins(List.of(
                "http://localhost:8080",
                "http://localhost:5432",
                "http://localhost:35729",
                "http://127.0.0.1:5501"
        ));

        // 🔁 Métodos HTTP permitidos
        config.setAllowedMethods(List.of(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS",
                "HEAD",
                "TRACE",
                "CONNECT"
        ));

        // 📩 Headers permitidos
        config.setAllowedHeaders(List.of(
                "Authorization",
                "Content-Type",
                "Accept"
        ));

        // 📤 Headers expuestos al cliente
        config.setExposedHeaders(List.of(
                "Authorization"
        ));

        // 🔐 Permitir credenciales (cookies / tokens)
        config.setAllowCredentials(true);

        // ⏱ Cache del preflight (1 hora)
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
