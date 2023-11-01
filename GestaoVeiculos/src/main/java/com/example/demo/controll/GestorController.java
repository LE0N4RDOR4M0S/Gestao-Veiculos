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

import com.example.demo.domain.Gestor;
import com.example.demo.service.GestorServices;

@Controller
@RequestMapping("/gestores")
public class GestorController {
	@Autowired
	GestorServices gestose;
	
	@Autowired
	Gestor gestor;
	
	/*-----------------Get para retornar as informações dos gestores*/
	@GetMapping
	private List<Gestor> retornarGestores(){
		return gestose.retornarGestores();
	}
	
	/*----------------Get para retornar apenas um dos gestores no bd*/
	@GetMapping("/{id}")
	public ModelAndView retornarG(@PathVariable Integer id){
		ModelAndView movi = new ModelAndView("dadosGestor");
		Gestor gestor = gestose.returnGestor(id).get();
		movi.addObject("gestor", gestor);
		return movi;
	}
	
	@GetMapping("/add")
	private String addGestor() {
		return "addGestor";
	}
	
	/*----------------Post para cadastrar um novo gestor no bd */
	@PostMapping("/add")
	private String addGestor(@ModelAttribute Gestor gestor) {
		gestose.addGestor(gestor);
		Integer id = gestor.getId();
		return "redirect:/auth/register/"+id;
	}

	/*-----------------Delete para apagar os dados de um gestor existente*/
	@GetMapping("/delete/{id}")
	public String deleteGestor(@PathVariable Integer id) {
		Gestor gestor = gestose.returnGestor(id).get();
		if (gestor.getRota() == null) {
			gestose.deleteGestor(id);
			return "redirect:/user";
		} else {
			return "redirect:/erroExclusao";
		}
	}
}
