package com.riwi.operations.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/greet")
public class HelloController {

    @GetMapping
    public String greet() {
        return "Hola Mundo";
    }
}
