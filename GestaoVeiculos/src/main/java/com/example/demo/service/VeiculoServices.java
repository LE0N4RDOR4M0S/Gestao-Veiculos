package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Veiculo;
import com.example.demo.repository.MotoristaRepository;
import com.example.demo.repository.VeiculoRepository;

@Service
public class VeiculoServices {

	@Autowired
	Veiculo veiculo;
	
	@Autowired
	VeiculoRepository veiculore;
	
	@Autowired
	MotoristaRepository motoristare;
	
	/*Função para retornar todos os veículos cadastrados no sistema*/
	public List<Veiculo> returnVeiculo(){
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		veiculore.findAll().forEach(veiculos::add);
		return veiculos;
	}
	
	/* Função para retornar um veiculo específico por placa*/
	public Veiculo buscaVeiculo(String placa){
		Optional<Veiculo> veiculoex = veiculore.findById(placa);
		Veiculo veiculo = veiculoex.get();
		return veiculo;
	}
	
	/* Função para retornar apenas os veiculos de determinado tipo*/
	public List<Veiculo> retornTipoVeiculo(String tipo){
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		veiculore.findByTipo(tipo).forEach(veiculos::add);
		return veiculos;
	}
	
	/* Função para retornar apenas os veiculos com acessibilidade*/
	public List<Veiculo> retornarComAcessibilidade(){
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		veiculore.findByAcessibilidade(true).forEach(veiculos::add);
		return veiculos;
	}
	
	public List<Veiculo> retornarQtdLugares(int qtdMin){
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		veiculore.findByQtdMaior(qtdMin).forEach(veiculos::add);
		return veiculos;
	}
	
	/*Função para cadastrar um veiculo*/
	public void addVeiculo(Veiculo veiculo){
		veiculo.setStatus("estacionado");
		veiculore.save(veiculo);
	}
	
	/*Função para alterar um veiculo ja existente*/
	public void atualizarVeiculo(String placa, Veiculo veiculoAtualizado) {
		Veiculo veiculo = buscaVeiculo(placa);
		veiculo.setTipo(veiculoAtualizado.getTipo());
        veiculo.setAcessibilidade(veiculoAtualizado.getAcessibilidade());
        veiculo.setEstacionamento(veiculoAtualizado.getEstacionamento());
        veiculo.setModelo(veiculoAtualizado.getModelo());
        veiculo.setAno(veiculoAtualizado.getAno());
        veiculo.setRenavam(veiculoAtualizado.getRenavam());
        veiculo.setQtd_lugares(veiculoAtualizado.getQtd_lugares());
        veiculo.setRota(veiculoAtualizado.getRota());
		veiculo.setStatus(veiculo.getStatus());

        veiculore.save(veiculo);
    }

	/* Função para deletar um veiculo existente*/
	public void deletarVeiculo(String placa){
		veiculore.deleteById(placa);
	}

	/* Função para retornar os veiculos pelo status */
	public List<Veiculo> retornarPStatus(String status){
		return veiculore.findBystatus(status);
	}
	
}
