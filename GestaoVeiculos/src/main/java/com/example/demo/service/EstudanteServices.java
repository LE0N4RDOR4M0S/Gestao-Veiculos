package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Estudante;
import com.example.demo.domain.Parada;
import com.example.demo.domain.Rota;
import com.example.demo.repository.EstudanteRepository;

@Service
public class EstudanteServices {
	
	@Autowired
	EstudanteRepository esture;
	
	@Autowired
	Estudante estudante;
	
	/* Função para retornar todos os estudantes*/
	public List<Estudante> returnEstudantes(){
		List<Estudante> estudantes = new ArrayList<Estudante>();
		esture.findAll().forEach(estudantes::add);
		return estudantes;
	}
	
	/* Função para retornar pelo id Rota*/
	public List<Estudante> returnEstudantesRota(Rota rota){
		List<Estudante> estudantes = new ArrayList<Estudante>();
		esture.findByRota(rota).forEach(estudantes::add);
		return estudantes;
	}
	
	/* Função para retornar pela parada*/
	public List<Estudante> returnEstudantesParada(Parada parada){
		List<Estudante> estudantes = new ArrayList<Estudante>();
		esture.findByParada(parada).forEach(estudantes::add);
		return estudantes;
	}
	
	/* Função para retornar pela acessibilidade*/
	public List<Estudante> returnEstudantesAcessi(){
		List<Estudante> estudantes = new ArrayList<Estudante>();
		esture.findByAcessibilidade(true).forEach(estudantes::add);
		return estudantes;
	}
	
	/* Função para retornar um estudante*/
	public Optional<Estudante> returnEst(Integer id){
		return esture.findById(id);
	}
	
	/* Função para cadastrar estudante*/
	public void addEstudante(Estudante estudante) {
		esture.save(estudante);
	}
	
	/* Função para editar estudante*/
	public void attEstudante(Integer id, Estudante nestudante) {
		Optional<Estudante> estudanteex = esture.findById(id);
		Estudante estudante = estudanteex.get();
		estudante.setNome(nestudante.getNome());
		estudante.setEnderecoResidencial(nestudante.getEnderecoResidencial());
		estudante.setAcessibilidade(nestudante.isAcessibilidade());
		estudante.setRota(nestudante.getRota());
		estudante.setParada(nestudante.getParada());
		estudante.setTurno(nestudante.getTurno());
		esture.save(estudante);
	}
	
	/* Função para deletar um estudante*/
	public void deleteEstudante(Integer id) {
		esture.deleteById(id);
	}
	
}
