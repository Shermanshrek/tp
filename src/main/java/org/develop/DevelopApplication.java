package org.develop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.develop.repository") // Путь к вашему UserRepo
@EntityScan(basePackages = "org.develop.model") // Путь к вашим сущностям
public class DevelopApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevelopApplication.class, args);
    }

}
