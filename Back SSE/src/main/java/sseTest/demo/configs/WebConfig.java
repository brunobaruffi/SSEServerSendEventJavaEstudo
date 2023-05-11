package sseTest.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // Coloque aqui a origem permitida para acessar sua API
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Coloque aqui os métodos HTTP permitidos
                .allowCredentials(true) // Permite o envio de cookies para a API
                .maxAge(36000); // Define o tempo de validade do preflight request em segundos
    }
}