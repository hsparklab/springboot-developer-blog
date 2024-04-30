package com.hsparklab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootDeveloperBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperBlogApplication.class, args);
    }
}