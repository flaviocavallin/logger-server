package com.example.logfileserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync
@Profile("!test")
public class WritingAsyncConfig {

    @Bean(name = "singleThreadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        return Executors.newSingleThreadExecutor();
    }


}
