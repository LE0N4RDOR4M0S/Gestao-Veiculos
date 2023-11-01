package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Estudante;
import com.example.demo.domain.Parada;
import com.example.demo.domain.Rota;

public interface EstudanteRepository extends CrudRepository<Estudante, Integer>{
	List<Estudante> findByAcessibilidade(Boolean acessibilidade);
	List<Estudante> findByRota(Rota rota);
	List<Estudante> findByParada(Parada parada);
	
}
