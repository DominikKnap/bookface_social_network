package pl.bookface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Main {

    public static final String LOCALHOST_3000 = "http://localhost:3000";

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("users").allowedOrigins(LOCALHOST_3000);
                registry.addMapping("login").allowedOrigins(LOCALHOST_3000);
                registry.addMapping("posts").allowedOrigins(LOCALHOST_3000);
                registry.addMapping("answers").allowedOrigins(LOCALHOST_3000);
            }
        };
    }
}
