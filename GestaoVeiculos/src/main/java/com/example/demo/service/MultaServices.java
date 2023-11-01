package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Data;
import com.example.demo.domain.Multa;
import com.example.demo.domain.Veiculo;
import com.example.demo.repository.MultaRepository;

@Service
public class MultaServices {
	@Autowired
	MultaRepository multare;
	
	@Autowired
	Multa multa;
	
	@Autowired
	Data data;
	
	/* Função para retornar todas as multas*/
	public List<Multa> retornarMultas(){
		List<Multa> multas = new ArrayList<Multa>();
		multare.findAll().forEach(multas::add);
		return multas;
	}
	
	public Multa retornarMulta(Integer id) {
		return multare.findById(id).get();
	}
	
	/* Função para retornar apenas as multas de determinado veiculo*/
	public List<Multa> retornarMultasVeiculo(Veiculo veiculo){
		List<Multa> multas = new ArrayList<Multa>();
		multare.findByVeiculo(veiculo).forEach(multas::add);
		return multas;
	}
	
	/* Função para retornar todas as multas de acordo com valor minimo*/
	public List<Multa> retornarMultaPValor(Double valorMin){
		List<Multa> multas = new ArrayList<Multa>();
		multare.findByQtdMaior(valorMin).forEach(multas::add);
		return multas;
	}
	
	/*Função para adicionar outra multa*/
	public void addMulta(Multa multa) {
		Date data = new Date();
		multa.setData(data);
		multare.save(multa);
	}
	
	/*Função para alterar algum atributo da multa*/
	public void attMulta(Integer id, Multa nmulta) {
		Optional<Multa> multaex = multare.findById(id);
		Multa multa = multaex.get();
		multa.setData(nmulta.getData());
		multa.setDescricao(nmulta.getDescricao());
		multa.setValor(nmulta.getValor());
		multa.setVeiculo(nmulta.getVeiculo());
		
		multare.save(multa);
	}
	
	/* Função para deletar/pagar uma multa*/
	public void deleteMulta(Integer id) {
		multare.deleteById(id);
	}
}
