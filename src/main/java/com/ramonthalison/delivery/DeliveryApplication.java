package com.ramonthalison.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeliveryApplication {

    // Ponto de entrada: inicia o contêiner Spring e registra os componentes da aplicação.
    public static void main(String[] args) {
        SpringApplication.run(DeliveryApplication.class, args);
    }
}
