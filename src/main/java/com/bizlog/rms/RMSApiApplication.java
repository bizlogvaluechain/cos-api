package com.bizlog.rms;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.util.TimeZone;

@EnableWebFlux
@EnableR2dbcRepositories
@SpringBootApplication
public class RMSApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RMSApiApplication.class, args);
    }

}
