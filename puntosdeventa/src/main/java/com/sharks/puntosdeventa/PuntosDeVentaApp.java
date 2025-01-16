package com.sharks.puntosdeventa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class PuntosDeVentaApp {

    public static void main(String[] args) {
        SpringApplication.run(PuntosDeVentaApp.class, args);
    }
}

