package com.avila.proposals;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProposalsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProposalsApplication.class, args);
    }
}
