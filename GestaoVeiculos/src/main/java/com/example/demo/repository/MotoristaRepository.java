package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Motorista;
import com.example.demo.domain.Rota;
import com.example.demo.domain.Veiculo;

public interface MotoristaRepository extends CrudRepository<Motorista, String>{
	List<Motorista> findByTurno(String turnoT);
	List<Motorista> findByRota(Rota rota);
}
