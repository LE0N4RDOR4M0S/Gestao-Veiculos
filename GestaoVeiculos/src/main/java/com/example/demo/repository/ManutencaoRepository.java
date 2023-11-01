package com.example.demo.repository;

import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Manutencao;
import com.example.demo.domain.Veiculo;

public interface ManutencaoRepository extends CrudRepository<Manutencao, Integer>{
	List<Manutencao> findByVeiculo(Veiculo veiculo);
}
