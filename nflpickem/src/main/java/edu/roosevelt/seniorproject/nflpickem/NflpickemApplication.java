package edu.roosevelt.seniorproject.nflpickem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NflpickemApplication {

    public static void main(String[] args) {
        SpringApplication.run(NflpickemApplication.class, args);
    }

}
