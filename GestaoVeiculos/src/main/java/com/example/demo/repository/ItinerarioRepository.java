package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Itinerario;
import com.example.demo.domain.Parada;
import com.example.demo.domain.Rota;

public interface ItinerarioRepository extends CrudRepository<Itinerario, Integer>{
	List<Itinerario> findByRota(Rota rota);
	List<Itinerario> findByParada(Parada parada);
}
