package com.example.demo.controll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sobre")
public class SobreControll {
	
	@GetMapping
	private String sobre() {
		return "sobre";
	}
}
