package com.polarbook.catalogservice.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HomeController {

    @GetMapping("/")
    String greeting() {
        return "Welcome to the book catalog!";
    }
}
