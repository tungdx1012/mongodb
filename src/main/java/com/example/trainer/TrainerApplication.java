package com.example.trainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TrainerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrainerApplication.class, args);
    }
}
