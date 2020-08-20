package com.proyectofinal.configurationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableConfigServer
@SpringBootApplication
public class ConfigurationserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationserverApplication.class, args);
    }

}
