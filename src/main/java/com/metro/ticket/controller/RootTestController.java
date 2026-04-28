package com.metro.ticket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootTestController {

    @GetMapping("/")
    public String home() {
        return "APP IS RUNNING";
    }
}