package com.carrotinstitutefooddelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CarrotInstituteFoodDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarrotInstituteFoodDeliveryApplication.class, args);
    }
}
