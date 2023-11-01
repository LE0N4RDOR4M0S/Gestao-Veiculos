package com.example.demo.controll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Veiculo;
import com.example.demo.service.ManutencaoServices;
import com.example.demo.service.MultaServices;
import com.example.demo.service.RotaServices;
import com.example.demo.service.VeiculoServices;

@Controller
@RequestMapping("/veiculos")
public class VeiculoControll {
	/*------------------------------------Injeções---------------------------------------*/
	
	@Autowired
	VeiculoServices veiculose;
	
	@Autowired
	RotaServices rotaServices;
	
	@Autowired
	Veiculo veiculo;
	
	@Autowired
	MultaServices multaServices;
	
	@Autowired
	ManutencaoServices manutencaoServices;
	
	/*-----------------------------------Mapeamento de funções--------------------------*/
	
	/* Get geral*/
	@GetMapping
	private ModelAndView retornarVeiculos(){
		ModelAndView movi = new ModelAndView("listagemVeiculos");
		movi.addObject("veiculos",veiculose.returnVeiculo());
		return movi;
	}
	/* Get apenas dos veiculos que possuirem acessibilidade*/
	@GetMapping("/acessibilidade")
	private List<Veiculo> rtnPorAcessbilidade(){
		return veiculose.retornarComAcessibilidade();
	}
	/* Get das informações do veiculo com a placa especifica*/
	@GetMapping("/{placa}")
	private ModelAndView rtnPorPlaca(@PathVariable String placa){
		ModelAndView movi = new ModelAndView("detalhesVeiculo");
		movi.addObject("veiculo",veiculose.buscaVeiculo(placa));
		movi.addObject("multas", multaServices.retornarMultasVeiculo(veiculose.buscaVeiculo(placa)));
		movi.addObject("manutencoes",manutencaoServices.retornarManutencaoVeiculo(veiculose.buscaVeiculo(placa)));
		return movi;
	}
	@GetMapping("/add")
	private ModelAndView addVeiculo() {
		ModelAndView movi = new ModelAndView("addVeiculo");
		movi.addObject("rotas", rotaServices.retornarRotas());
		return movi;
	}
    
	/* Post das informações em um veiculo*/
	@PostMapping("/add")
	private String addVeiculo (@ModelAttribute Veiculo veiculo) {
		try {
			veiculose.addVeiculo(veiculo);
			return "redirect:/salvoSucesso";
		} catch (Exception e) {
			return "redirect:/erroNoCadastro";
		}
	}
	
	@GetMapping("update/{placa}")
	private ModelAndView updtaVeiculo(@PathVariable String placa) {
		ModelAndView movi = new ModelAndView("updVeiculo");
		Veiculo veiculo = veiculose.buscaVeiculo(placa);
		movi.addObject("veiculo", veiculo);
		movi.addObject("rotas", rotaServices.retornarRotas());
		return movi;
	}
	/* Put dos dados de um dos veiculos*/
	@PostMapping("/update/{placa}")
	private String updVeiculo(@PathVariable String placa, @ModelAttribute Veiculo veiculo) {
		try {
			veiculose.atualizarVeiculo(placa, veiculo);
			return "redirect:/veiculos/"+placa;
		}catch (Exception e) {
			return "redirect:/atualizacaoFalhou";
		}
	}
	/* Delete de um dos veiculos*/
	@GetMapping("/delete/{placa}")
	private String deleteVeiculo(@PathVariable String placa) {
		Veiculo veiculo = veiculose.buscaVeiculo(placa);
		if (veiculo.getMotorista().isEmpty()) {
			veiculose.deletarVeiculo(placa);
			return "redirect:/veiculos";
		} else {
			return "redirect:/erroExclusao";
		}
	}
	
	@GetMapping("/alterStatus/{placa}")
	private String alterarStatus(@PathVariable String placa) {
		Veiculo veiculo = veiculose.buscaVeiculo(placa);
		if ("estacionado".equals(veiculo.getStatus())) {
			veiculo.setStatus("atividade");
		}
		else { 
			veiculo.setStatus("estacionado");
		}
		veiculose.atualizarVeiculo(placa, veiculose.buscaVeiculo(placa));
		return "redirect:/veiculos/"+placa;
		
	}
	
	

}
