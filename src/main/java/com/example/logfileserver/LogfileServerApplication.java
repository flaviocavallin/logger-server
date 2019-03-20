package com.example.logfileserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class LogfileServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogfileServerApplication.class, args);
    }
}
