package com.example.demo.controll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Motorista;
import com.example.demo.service.MotoristaServices;
import com.example.demo.service.RotaServices;
import com.example.demo.service.VeiculoServices;

@Controller
@RequestMapping("/motorista")
public class MotoristaController {
	@Autowired
	MotoristaServices motose;

	@Autowired
	RotaServices rotaServices;

	@Autowired
	VeiculoServices veiculoServices;
	
	/*----------------------------Get de todos os motoristas cadastrados no sistema*/
	@GetMapping
	private ModelAndView returnMotor(){
		ModelAndView movi = new ModelAndView("listagemMotorista");
		movi.addObject("motorista",motose.retornarMotoristas());
		return movi;
	}
	
	/*-----------------------------Get de um motorista pelo id*/
	@GetMapping("/{cnh}")
	public ModelAndView retUmMotor(@PathVariable String cnh){
		ModelAndView movi = new ModelAndView("dadosMotorista");
		movi.addObject("motorista", motose.retornMoto(cnh));
		return movi;
	}
	
	/*------------------------------Get de todos os motoristas em uma rota*/
	@GetMapping("/rotas/{id}")
	private List<Motorista> retMotorRota(@PathVariable Integer id){
		return motose.returnMtoRota(id);
	}
	
	/*------------------------------ Get de todos os motoristas por turno*/
	@GetMapping("/turnos/{turno}")
	private List<Motorista> retMotorTurno(@PathVariable String turno){
		return motose.retornarPTurno(turno);
	}
	
	@GetMapping("/add")
	private ModelAndView addMoto(){
		ModelAndView movi = new ModelAndView("addMotorista");
		movi.addObject("rotas", rotaServices.retornarRotas());
		movi.addObject("veiculos", veiculoServices.returnVeiculo());
		return movi;
	}

	/*-------------------------------Post para cadastrar os motoristas*/
	@PostMapping("/add")
	private String addMotorista(@ModelAttribute Motorista motorista){
		try{
			motose.addMotorista(motorista);
			return "redirect:/motorista";
		} catch (Exception e) {
			return "redirect:/erroNoCadastro";
		}
	}
	
	/*------------------------------Put para alterar um motorista no bd*/
	@GetMapping("/update/{cnh}")
	private ModelAndView updateMotorista(@PathVariable String cnh) {
		ModelAndView movi = new ModelAndView("UpdtMotorista");
		movi.addObject("motorista", motose.retornMoto(cnh));
		movi.addObject("rotas",rotaServices.retornarRotas());
		movi.addObject("veiculos", veiculoServices.returnVeiculo());
		return movi;
	}
	
	@PostMapping("/update/{cnh}")
	private String updMotorista(@PathVariable String cnh, @ModelAttribute Motorista motorista){
		try {
			motose.attMotorista(cnh, motorista);
			return "redirect:/salvoSucesso";
		}catch(Exception e) {
			return "erroAtualizacao";
		}
	}
	
	/*------------------------------Delete para apagar um motorista no bd*/
	@GetMapping("/delete/{cnh}")
	public String deleteMotorista(@PathVariable String cnh) {
		motose.deletarMotoristas(cnh);
		return "redirect:/motorista";
	}
		
	
}
