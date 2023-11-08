package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.domain.Estudante;
import com.example.demo.domain.Parada;
import com.example.demo.domain.Rota;

public interface EstudanteRepository extends PagingAndSortingRepository<Estudante, Integer>{
	List<Estudante> findByAcessibilidade(Boolean acessibilidade);
	List<Estudante> findByRota(Rota rota);
	List<Estudante> findByParada(Parada parada);
	Optional<Estudante> findById(Integer id);
	Estudante save(Estudante estudante);
	void deleteById(Integer id);
	
}
