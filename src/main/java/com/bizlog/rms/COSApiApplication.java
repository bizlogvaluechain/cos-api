package com.bizlog.rms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class COSApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(COSApiApplication.class, args);
    }

}
