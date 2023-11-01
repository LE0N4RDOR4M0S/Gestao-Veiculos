package com.example.demo.controll;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Itinerario;
import com.example.demo.domain.Parada;
import com.example.demo.domain.Rota;
import com.example.demo.service.ItinerarioServices;
import com.example.demo.service.ParadaServices;
import com.example.demo.service.RotaServices;

@Controller
@RequestMapping("/itinerario")
public class ItinerarioController {
	@Autowired
	ItinerarioServices itinese;
	
	@Autowired
	Rota rota;
	
	@Autowired
	RotaServices rotase;
	
	@Autowired
	Parada parada;
	
	@Autowired
	ParadaServices paradase;

	
	
	
	/*-------------------------------Get dos itinerarios de uma rota*/
	@GetMapping("/rotas/{id}")
	private ModelAndView returnIdRota(@PathVariable Integer id){
		Rota rota = rotase.retornarRota(id);
		ModelAndView movi = new ModelAndView("Itinerarios");
		movi.addObject("itinerario",itinese.returnItinerariosRotaId(rota));
		movi.addObject("rota",rota);
		return movi;
	}
	
	/*-------------------------------Get dos itinerários de uma parada*/
	@GetMapping("/parada/{id}")
	private List<Itinerario> returnParadaRota(@PathVariable Integer id){
		Optional<Parada> paradaex = paradase.buscaParada(id);
		Parada parada = paradaex.get();
		return itinese.returnItinerariosParadaId(parada);
	}
	
	@GetMapping("/add/{id}")
	private ModelAndView addItt(@PathVariable Integer id){
		ModelAndView movi = new ModelAndView("addItinerario");
		movi.addObject("rota", rotase.retornarRota(id));
		movi.addObject("parada", paradase.retornarParadas());
		return movi;
	}


	/*-------------------------------Post de um novo itinerário no bd*/
	@PostMapping("/add")
	private String addItinerario(@ModelAttribute Itinerario itinerario) {
		Rota rota = itinerario.getRota();
		itinese.addItinerario(itinerario);
		return "redirect:/itinerario/rotas/"+rota.getId();
	}
	
	/*-------------------------------Put para atualizar */
	@PutMapping("{id}")
	private String updItinerario(@PathVariable Integer id, @RequestBody Itinerario itinerario) {
		itinese.attItinerario(id, itinerario);
		return "Atualizado";
	}
	
	/*-------------------------------Delete para apagar itinerarios ja existentes*/
	@GetMapping("/delete/{id}")
	private String deleteItinerario(@PathVariable Integer id) {
		Rota rota = itinese.retornaIt(id).getRota();
		itinese.deleteItinerario(id);
		return "redirect:/itinerario/rotas/"+rota.getId();
	}
	
}
