package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Rota;
import com.example.demo.repository.RotaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RotaServices {
	@Autowired
	Rota rota;
	
	@Autowired
	RotaRepository rotare;
	
	/* Função para retornar todas as rotas cadastradas no sistema*/
	public List<Rota> retornarRotas(){
		List<Rota> rotas = new ArrayList<Rota>();
		rotare.findAll().forEach(rotas::add);
		return rotas;
	}
	
	/* Função para retornar rota pelo Id*/
	public Rota retornarRota(Integer id){
		Rota rota = rotare.findById(id).get();
		return rota;
	}
	
	/* Função para cadastrar uma Rota*/
	public void addRota(Rota rota) {
		
		rotare.save(rota);
	}
	
	/* Função para editar uma rota*/
	public void editarRota(Rota newrota, Integer id) {
		Rota rota = retornarRota(id);
		rota.setDestino(newrota.getDestino());
		rota.setOrigem(newrota.getOrigem());
		rota.setGestor(newrota.getGestor());
		
		rotare.save(rota);
	}
	
	/* Função para deletar uma rota existente*/
	public void deletarRota(Integer id){
		rotare.deleteById(id);
	}	
}
