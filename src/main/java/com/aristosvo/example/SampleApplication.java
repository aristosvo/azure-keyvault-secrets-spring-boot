package com.aristosvo.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class SampleApplication implements CommandLineRunner {


    @Value("${your.property.name}")
    private String mySecretProperty;

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    public void run(String... varl) throws Exception {
        System.out.println("property yourSecretPropertyName in Azure Key Vault: " + mySecretProperty);
    }

}