package com.example.demo.controll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class SalvoSucesso {
	@GetMapping("/salvoSucesso")
	private String telaSucesso() {
		return "SalvoSucesso";
	}
	
	@GetMapping("/erroNoCadastro")
	private String telaErroCad() {
		return "erroNoCadastro";
	}
	
	@GetMapping("/erroExclusao")
	private String erroEx() {
		return "error";
	}
	
	@GetMapping("/atualizacaoFalhou")
	private String attFail() {
		return "erroAtualizacao";
	}
}
