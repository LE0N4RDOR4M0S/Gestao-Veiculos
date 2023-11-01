package com.example.demo.controll;

 
import java.util.ArrayList;
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

import com.example.demo.domain.Itinerario;
import com.example.demo.domain.Parada;
import com.example.demo.domain.Rota;
import com.example.demo.service.GestorServices;
import com.example.demo.service.ItinerarioServices;
import com.example.demo.service.RotaServices;
import com.google.maps.model.LatLng;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/rotas")
public class RotaCrontroll {
	/*-----------------------Injeções*/
	@Autowired 
	RotaServices rotase;
	
	@Autowired
	Rota rota;
	
	@Autowired
	GestorServices gestorServices;
	
	@Autowired
	ItinerarioServices itinerarioServices;
	
	/*-------- Get para retornar todos as rotas cadastradas no sistema*/
	@GetMapping
	private ModelAndView retornarRotas(){
		ModelAndView movi = new ModelAndView("listagemRotas");
		movi.addObject("rotas",rotase.retornarRotas());
		return movi;
	}
	
	/*---------Get para retornar uma rota pelo id*/
	@GetMapping("/{id}")
	private ModelAndView retornarRota(@PathVariable Integer id){
		ModelAndView movi = new ModelAndView("detalhesRota");
		Rota rota = rotase.retornarRota(id);
		List<Itinerario> itinerarios = itinerarioServices.returnItinerariosRotaId(rota);
		List<LatLng> coordenadasParadas = new ArrayList<>();
		for (Itinerario itinerario : itinerarios) {
		    Parada parada = itinerario.getParada();
		    LatLng coordenada = new LatLng(parada.getLatitude(), parada.getLongitude());
		    coordenadasParadas.add(coordenada);
		}
		movi.addObject("coordenadas",coordenadasParadas);
		movi.addObject("rota",rotase.retornarRota(id));
		System.out.println("Coordenadas Paradas: " + coordenadasParadas);
		return movi;
	}
	
	@GetMapping("/add")
	private ModelAndView addRota() {
		ModelAndView movi = new ModelAndView("addRota");
		movi.addObject("gestor", gestorServices.retornarGestores());
		return movi;
	}
	
	/*----------Post para cadastrar uma nova rota no bd*/
	@PostMapping("/add")
	private String addRota(@ModelAttribute @Valid Rota rota) {
	    try {
	    	//rota.setGestor(gestorServices.returnGestor(rota.getIdGestor()));
	        rotase.addRota(rota);
	        return "redirect:/salvoSucesso";
	    } catch (Exception ex) {
	        // Lida com exceções durante a inserção no banco de dados
	        return "redirect:/erroNoCadastro";
	    }
	}

	
	/*----------Put para alterar uma rota existente*/
	@PutMapping("/update/{id}")
	private String updRota(@PathVariable Integer id, @RequestBody Rota rota) {
		rotase.editarRota(rota, id);
		return "Rota atualizada";
	}
	
	/*------------Delete para apagar uma rota*/
	@GetMapping("/delete/{id}")
	private String deleteRota(@PathVariable Integer id) {
	    Rota rota = rotase.retornarRota(id);
	    if (rota.getVeiculo().isEmpty() && rota.getEstudante().isEmpty() && rota.getMotorista().isEmpty()) {
	            rotase.deletarRota(id);
	            return "redirect:/rotas";
	    }else {
	    	return "/erroExclusao";
	    }
	}
	
}
