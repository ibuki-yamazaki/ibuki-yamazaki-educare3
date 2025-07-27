package com.example;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
            SpringApplication.run(Application.class, args);

        ProductService userService = context.getBean(ProductService.class);
        List<Products> list = userService.findAll();
        for (Products u : list) {
            System.out.println(u.getProductInfo());
        }
    }
}


