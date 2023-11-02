package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Parada;
//import com.example.demo.domain.Rota;
import com.example.demo.domain.Veiculo;
import com.example.demo.repository.ParadaRepository;

@Service
public class ParadaServices {
	@Autowired
	ParadaRepository paradare;
	
	@Autowired
	Parada parada;
	
	@Autowired
	Veiculo veiculo;
	
	/* Função para retornar todas as paradas */
	public List<Parada> retornarParadas(){
		List<Parada> paradas = new ArrayList<Parada>();
		paradare.findAll().forEach(paradas::add);
		return paradas;
	}
	
	/* Função para retornar uma parada em especifico pelo id*/
	public Optional<Parada> buscaParada(Integer id){
		Optional<Parada> parada = paradare.findById(id);
		return parada;
	}
	
	
	/* Função para cadastrar uma nova parada*/
	public void addParada(Parada parada){
		paradare.save(parada);
	}
	
	/* Função para alterar uma parada*/
	public void attParada(Integer id, Parada nparada){
		Optional<Parada> paradaex = paradare.findById(id);
		Parada parada = paradaex.get();
		parada.setEndereco(nparada.getEndereco());
		
		paradare.save(parada);
	}
	
	/* Função para deletar uma parada*/
	public void deleteParada(Integer id){
		paradare.deleteById(id);
	}
}
