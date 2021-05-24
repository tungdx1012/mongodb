package com.example.trainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class TrainerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrainerApplication.class, args);
    }
}
