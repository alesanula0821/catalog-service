package com.polarbook.catalogservice;


import com.polarbook.catalogservice.configuration.PolarProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HomeController {
    private final PolarProperties properties;

    HomeController(PolarProperties properties) {
        this.properties = properties;
    }

    @GetMapping("/")
    String greeting() {
        return properties.getGreeting();
    }
}