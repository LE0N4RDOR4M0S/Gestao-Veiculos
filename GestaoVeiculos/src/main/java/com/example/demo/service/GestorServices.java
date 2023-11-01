package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Gestor;
import com.example.demo.repository.GestorRepository;

@Service
public class GestorServices {
	@Autowired
	GestorRepository gestore;
	
	@Autowired
	Gestor gestor;
	
	/* Função para retornar todos os gestores cadastrados*/
	public List<Gestor> retornarGestores(){
		List<Gestor> gestores = new ArrayList<Gestor>();
		gestore.findAll().forEach(gestores::add);
		return gestores;
	}
	/* Função para retornar um gestor de id especifico*/
	public Optional<Gestor> returnGestor(Integer id){
		return gestore.findById(id);
	}
	
	/* Função para cadastrar um novo gestor*/
	public void addGestor(Gestor gestor) {
		gestore.save(gestor);
	}
	
	/* Função para editar um gestor*/
	public void attGestor(Integer id, Gestor ngestor) {
		Optional<Gestor> gestorEx = gestore.findById(id);
		Gestor gestor = gestorEx.get();
		gestor.setEmail(ngestor.getEmail());
		gestor.setNome(ngestor.getNome());
		gestore.save(gestor);
	}
	
	/* Função para deletar um gestor*/
	public void deleteGestor(Integer id) {
		gestore.deleteById(id);
	}
}
