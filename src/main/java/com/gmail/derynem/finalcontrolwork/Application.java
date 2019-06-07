package com.gmail.derynem.finalcontrolwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.gmail.derynem.finalcontrolwork")
@EntityScan(basePackages = {"com.gmail.derynem.finalcontrolwork.repository.model"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}