package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Data;
import com.example.demo.domain.Manutencao;
import com.example.demo.domain.Veiculo;
import com.example.demo.repository.ManutencaoRepository;

@Service
public class ManutencaoServices {
	@Autowired
	ManutencaoRepository manure;
	
	@Autowired 
	Manutencao manutencao;
	
	@Autowired 
	Data data;
	
	/* encontrar veiculo da manutencao*/
	public Veiculo encontrarVeiculo(Integer id) {
		Manutencao manutencao = manure.findById(id).get();
		Veiculo veiculo = manutencao.getVeiculo();
		return veiculo;
	}
	
	/* Função para retornar todas as manutenções feitas*/
	public List<Manutencao> retornarManutencoes(){
		List<Manutencao> manutencoes = new ArrayList<Manutencao>();
		manure.findAll().forEach(manutencoes::add);
		return manutencoes;
	}
	
	/*Função para retornar apenas as manutenções feitas em um veiculo*/
	public List<Manutencao> retornarManutencaoVeiculo(Veiculo veiculo){
		List<Manutencao> manutencoes = new ArrayList<Manutencao>();
		manure.findByVeiculo(veiculo).forEach(manutencoes::add);
		return manutencoes;
	}
	
	/* Função para cadastrar uma nova manutenção */
	public void addManutencao(Manutencao manutencao) {
		Date data = new Date();
		manutencao.setDataEntrada(data);
		manure.save(manutencao);
	}
	
	/* Função para concluir uma manutencao*/
	public void attManutencao(Integer id) {
		Date data = new Date();
		Optional<Manutencao> manutencaoex = manure.findById(id);
		Manutencao manutencao = manutencaoex.get();
		manutencao.setDataEntrada(manutencao.getDataEntrada());
		manutencao.setDataSaida(data);
		manutencao.setDescricao(manutencao.getDescricao());
		manutencao.setVeiculo(manutencao.getVeiculo());
		manure.save(manutencao);
	}
	
	/* Funcao para deletar uma manutencao*/
	public void deleteManutencao(Integer id) {
		manure.deleteById(id);
	}
	
}
