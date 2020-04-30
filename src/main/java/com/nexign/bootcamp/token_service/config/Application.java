package com.nexign.bootcamp.token_service.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Yaroslav.Zakharenko
 * @since 30.04.2020 18:34
 */
@SpringBootApplication(scanBasePackages = "com.nexign.bootcamp.token_service")
@EnableJpaRepositories("com.nexign.bootcamp.token_service.dao")
@EntityScan("com.nexign.bootcamp.token_service.entities")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
