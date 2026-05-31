package org.example.personalfinanceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PersonalFinanceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalFinanceAppApplication.class, args);
    }

}
