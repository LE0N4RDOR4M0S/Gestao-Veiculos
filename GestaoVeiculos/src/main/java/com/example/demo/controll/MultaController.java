package com.example.demo.controll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Multa;
import com.example.demo.domain.Veiculo;
import com.example.demo.repository.MultaRepository;
import com.example.demo.service.MultaServices;
import com.example.demo.service.VeiculoServices;

@Controller
@RequestMapping("/multa")
public class MultaController {
	
	@Autowired
	MultaServices multase;
	
	@Autowired
	MultaRepository multare;
	
	@Autowired
	Multa multa;
	
	@Autowired
	Veiculo veiculo;
	
	@Autowired
	VeiculoServices veiculose;
	
	/*------------------------------Get de todas as multas cadastradas*/
	@GetMapping
	private List<Multa> retornarMultas(){
		return multase.retornarMultas();
	}
	
	/*------------------------------Get de todas as multas em um dos veiculos*/
	@GetMapping("/{placa}")
	private List<Multa> retornarM(@PathVariable String placa){
		Veiculo veiculo = veiculose.buscaVeiculo(placa);
		return multase.retornarMultasVeiculo(veiculo);
	}
	
	/*-----------------------------Get de todas as multas acima de um certo valor*/
	@GetMapping("/valor/{valor}")
	private List<Multa> retornarPValor(@PathVariable Double valor){
		return multase.retornarMultaPValor(valor);
	}
	
	@GetMapping("/add/{placa}")
	private ModelAndView addMulta(@PathVariable String placa) {
		ModelAndView movi = new ModelAndView("addMulta");
		movi.addObject("veiculo",veiculose.buscaVeiculo(placa));
		return movi;
	}
	
	/*-----------------------------Post para cadastrar multas no bd*/
	@PostMapping("/add")
	private String addMulta(@ModelAttribute Multa multa) {
		try {
			multase.addMulta(multa);
			return "redirect:/salvoSucesso";
		}catch(Exception e) {
			return "redirect:/erroNoCadastro";
		}
	}
	
	/*------------------------------Put para alteração de alguma multa em específico*/
	@PutMapping("/update/{id}")
	private String updMulta(@PathVariable Integer id, @RequestBody Multa multa) {
		multase.attMulta(id, multa);
		return "Multa Alterada";
	}
	
	/*-------------------------------Delete para apagar uma multa existente*/
	@GetMapping("/delete/{id}")
	private String deleteMulta(@PathVariable Integer id) {
		Veiculo veiculo = multase.retornarMulta(id).getVeiculo();
		String placa = veiculo.getPlaca();
		multase.deleteMulta(id);
		return "redirect:/veiculos/"+placa;
	}
}
