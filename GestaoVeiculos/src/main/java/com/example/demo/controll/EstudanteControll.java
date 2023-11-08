package com.example.demo.controll;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Estudante;
import com.example.demo.domain.Parada;
import com.example.demo.domain.Rota;
import com.example.demo.service.EstudanteServices;
import com.example.demo.service.ParadaServices;
import com.example.demo.service.RotaServices;

@Controller
@RequestMapping("/estudante")
public class EstudanteControll {
	@Autowired
	EstudanteServices estudantese;
	
	@Autowired
	RotaServices rotase;
	
	@Autowired
	ParadaServices parase;
	
	
	/*-----------------Get de todos os estudantes cadastrados*/
	@GetMapping
	private ModelAndView returnEstudantes(@RequestParam(defaultValue = "0") int page){
		ModelAndView movi = new ModelAndView("listagemEstudantes");
		movi.addObject("estudante", estudantese.returnEstudantes(page));
		return movi;
	}
	
	/*----------------Get de um estudante pelo id*/
	@GetMapping("/{id}")
	public ModelAndView retornEst(@PathVariable Integer id){
		Estudante estudante = estudantese.returnEst(id).get();
		ModelAndView movi = new ModelAndView("dadosEstudante");
		movi.addObject("estudante", estudante);
		return movi;
	}
	
	/*----------------Get dos estudantes de uma rota*/
	@GetMapping("/rota/{id}")
	private List<Estudante> returnPorRota(@PathVariable Integer id){
		Rota rota = rotase.retornarRota(id);
		return estudantese.returnEstudantesRota(rota);
	}
	
	/*---------------Get dos estudantes de uma parada*/
	@GetMapping("/parada/{id}")
	private List<Estudante> returnPorParada(@PathVariable Integer id){
		Optional<Parada> paradaex = parase.buscaParada(id);
		Parada parada = paradaex.get();
		return estudantese.returnEstudantesParada(parada);
	}
	
	/*----------------Get dos estudantes com acessibilidade*/
	@GetMapping("/acessibilidade")
	private List<Estudante> returnPorAcess(){
		return estudantese.returnEstudantesAcessi();
	}
	
	@GetMapping("/add")
	private ModelAndView addEstudante(){
		ModelAndView movi = new ModelAndView("addEstudante");
		movi.addObject("paradas", parase.retornarParadas());
		movi.addObject("rotas", rotase.retornarRotas());
		return movi;
	}

	/*------------------Post de um novo estudante*/
	@PostMapping("/add")
	private String addEstudante(@ModelAttribute Estudante estudante) {
		try{
			estudantese.addEstudante(estudante);
			return "redirect:/estudante";
		} catch (Exception e){
			return "redirect:/erroNoCadastro";
		}
	}
	
	@GetMapping("/update/{idMatricula}")
	private ModelAndView updateEstudante(@PathVariable Integer idMatricula) {
		ModelAndView movi = new ModelAndView("updEstudante");
		Estudante estudante = estudantese.returnEst(idMatricula).get();
		movi.addObject("estudante", estudante);
		movi.addObject("paradas", parase.retornarParadas());
		movi.addObject("rotas", rotase.retornarRotas());
		return movi;
		
	}
	
	/*-----------------Put de um estudante*/
	@PostMapping("/update/{idMatricula}")
	private String updEstudante(@PathVariable Integer idMatricula, @ModelAttribute Estudante estudante) {
		try {
			estudantese.attEstudante(idMatricula, estudante);
			return "redirect:/estudante/"+idMatricula;
		}catch(Exception e) {
			return "erroAtualizacao";
		}
	}
	
	/*-----------------Delete de um estudante*/
	@GetMapping("/delete/{id}")
	public String dltEstudante(@PathVariable Integer id) {
		estudantese.deleteEstudante(id);
		return "redirect:/estudante";
	}
}
