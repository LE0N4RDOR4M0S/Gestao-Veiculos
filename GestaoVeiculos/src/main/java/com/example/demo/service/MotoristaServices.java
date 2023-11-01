package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Motorista;
import com.example.demo.domain.Rota;
import com.example.demo.repository.MotoristaRepository;
import com.example.demo.repository.RotaRepository;

@Service
public class MotoristaServices {

	@Autowired
	MotoristaRepository motore;
	
	@Autowired
	Motorista motorista;
	
	@Autowired
	Rota rota;
	
	@Autowired
	RotaRepository rotare;
	
	/*Função para retornar todos os motoristas*/
	public List<Motorista> retornarMotoristas(){
		List<Motorista> motoristas = new ArrayList<Motorista>();
		motore.findAll().forEach(motoristas::add);
		return motoristas;
	}
	
	/* Função para retornar um motorista por id*/
	public Motorista retornMoto(String cnh){
		Motorista motorista = motore.findById(cnh).get();
		return motorista;
	}
	
	/* Função para retornar motoristas de uma rota*/
	public List<Motorista> returnMtoRota(Integer id){
		List<Motorista> motoristas = new ArrayList<Motorista>();
		Optional<Rota> rotaex = rotare.findById(id);
		Rota rota = rotaex.get();
		motore.findByRota(rota).forEach(motoristas::add);
		return motoristas;
	}
	
	/* Função para retornar apenas os motoristas por turno*/
	public List<Motorista> retornarPTurno(String turnoT){
		return motore.findByTurno(turnoT);
	}
	
	/* Função para cadastrar motoristas*/
	public void addMotorista(Motorista motorista) {
		motore.save(motorista);
	}
	
	/* Função para atualizar os motoristas*/
	public void attMotorista(String cnh, Motorista nmotorista) {
		Optional<Motorista> motoristaex = motore.findById(cnh);
		Motorista motorista = motoristaex.get();
		motorista.setContato(nmotorista.getContato());
		motorista.setEndereco(nmotorista.getEndereco());
		motorista.setNome(nmotorista.getNome());
		motorista.setRota(nmotorista.getRota());
		motorista.setTurno(nmotorista.getTurno());
		motorista.setVeiculo(nmotorista.getVeiculo());
		
		motore.save(motorista);
	}
	
	/*Função para deletar um motorista no banco d*/
	public void deletarMotoristas(String cnh) {
		motore.deleteById(cnh);
	}
}
