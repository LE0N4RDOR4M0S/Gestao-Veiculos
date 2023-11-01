package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Itinerario;
import com.example.demo.domain.Parada;
import com.example.demo.domain.Rota;
import com.example.demo.repository.ItinerarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItinerarioServices {
	
	@Autowired
	Itinerario itinerario;
	
	@Autowired
	ItinerarioRepository itinere;

	/* Função para retornar todos os itinerários*/
	public List<Itinerario> returnItinerarios(){
		List<Itinerario> itinerarios = new ArrayList<Itinerario>();
		itinere.findAll().forEach(itinerarios::add);
		return itinerarios;
	}
	
	public Itinerario retornaIt(Integer id) {
		return itinere.findById(id).get();
	}
	
	/* Função para retornar todos os itinerários em uma rota*/
	public List<Itinerario> returnItinerariosRotaId(Rota rota){
		List<Itinerario> itinerarios = new ArrayList<Itinerario>();
		itinere.findByRota(rota).forEach(itinerarios::add);
		return itinerarios;
	}
	
	/* Função para retornar todos os itinerários em uma parada*/
	public List<Itinerario> returnItinerariosParadaId(Parada paradaId){
		List<Itinerario> itinerarios = new ArrayList<Itinerario>();
		itinere.findByParada(paradaId).forEach(itinerarios::add);
		return itinerarios;
	}
	
	/* Função para cadastrar um novo itinerário*/
	public void addItinerario(Itinerario itinerario) {
		itinere.save(itinerario);
	}
	
	/* Função para editar um itinerário existente*/
	public void attItinerario(Integer id, Itinerario nitinerario) {
		Optional<Itinerario> itinerarioex = itinere.findById(id);
		Itinerario itinerario = itinerarioex.get();
		itinerario.setHora(nitinerario.getHora());
		itinerario.setParada(nitinerario.getParada());
		itinerario.setRota(nitinerario.getRota());
		itinere.save(itinerario);
	}
	
	/* Função para deletar um itinerário existente*/
	public void deleteItinerario(Integer id) {
		itinere.deleteById(id);
	}
}
