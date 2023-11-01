package com.example.demo.domain;

import java.time.LocalDateTime; 

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Data {
	@GetMapping("/currentDate")
    public LocalDateTime getCurrentDate() {
        return LocalDateTime.now();
    }
}
