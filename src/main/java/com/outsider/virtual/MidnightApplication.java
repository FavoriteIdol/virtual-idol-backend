package com.outsider.virtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MidnightApplication {

    public static void main(String[] args) {
        SpringApplication.run(MidnightApplication.class, args);
    }

}
