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

import com.example.demo.domain.Manutencao;
import com.example.demo.domain.Veiculo;
import com.example.demo.service.ManutencaoServices;
import com.example.demo.service.VeiculoServices;

@Controller
@RequestMapping("/manutencao")
public class ManutencaoController {
	@Autowired
	ManutencaoServices manuse;
	
	@Autowired
	Manutencao manutencao;
	
	@Autowired
	Veiculo veiculo;
	
	@Autowired
	VeiculoServices veicuse;
	
	/*--------------------------------Get de todas as manutenções cadastradas no bd*/
	@GetMapping
	private ModelAndView returnManutencao(){
		ModelAndView movi = new ModelAndView("listagemManutencao");
		movi.addObject("manutencao", manuse.retornarManutencoes());
		return movi;
		
	}
	
	/*--------------------------------Get de todas as manutencoes feitas em um veiculo */
	@GetMapping("/{placaV}")
	private List<Manutencao> returnManutencaoVeiculo(@PathVariable String placaV){
		Veiculo veiculo = veicuse.buscaVeiculo(placaV);
		return manuse.retornarManutencaoVeiculo(veiculo);
	}
	
	@GetMapping("/add/{placa}")
	private ModelAndView addManutencao(@PathVariable String placa) {
		ModelAndView movi = new ModelAndView("addManutencao");
		movi.addObject("veiculo", veicuse.buscaVeiculo(placa));
		return movi;
	}
	
	/*--------------------------------Post para cadastrar uma nova manutencao*/
	@PostMapping("/add")
	private String addManutencao(@ModelAttribute Manutencao manutencao){
		try {
			manuse.addManutencao(manutencao);
			Veiculo veiculo = manuse.encontrarVeiculo(manutencao.getId());
			veicuse.atualizarVeiculo(veiculo.getPlaca(), veiculo);
			return "salvoSucesso";
		}catch (Exception e) {
			return "erroNoCadastro";
		}
	}
	
	/*--------------------------------Put para concluir uma manutenção*/
	@GetMapping("/concluir/{id}")
	private String updManutencao(@PathVariable Integer id) {
		Veiculo veiculo = manuse.encontrarVeiculo(id);
		manuse.attManutencao(id);
		veiculo.setStatus("estacionado");
		veicuse.atualizarVeiculo(veiculo.getPlaca(), veiculo);
		return "redirect:/veiculos/"+veiculo.getPlaca();
	}
	
	/*--------------------------------Delete para apagar uma manutencao cadastrada*/
	@GetMapping("/delete/{id}")
	private String deleteManutencao(@PathVariable Integer id) {
		Veiculo veiculo = manuse.encontrarVeiculo(id);
		manuse.deleteManutencao(id);
		if (veiculo.getStatus() == "manutencao") {
			veiculo.setStatus("estacionado");
		}
		return "redirect:/veiculos/"+veiculo.getPlaca();
	}
}
