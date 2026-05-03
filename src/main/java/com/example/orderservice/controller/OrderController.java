package com.example.orderservice.controller;

import com.example.orderservice.model.User;
import com.example.orderservice.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final WebClient webClient;

    @Value("${app.services.user-service.base-url}")
    private String userServiceUrl;

    public OrderController() {
        this.webClient = WebClient.builder().build();
    }
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
    @GetMapping("/user/{id}")
    public String getUserFromOrderService(@PathVariable Long id) {
        System.out.println("inside function");
        return webClient.get()
                .uri(userServiceUrl + "/users")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}