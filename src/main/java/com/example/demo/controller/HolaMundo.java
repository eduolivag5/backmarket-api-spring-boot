package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

@RestController
@RequestMapping("/api")
public class HolaMundo {

    @GetMapping("/hola")
    public String holaMundo() {
        return "Hola mundo";
    }
}
