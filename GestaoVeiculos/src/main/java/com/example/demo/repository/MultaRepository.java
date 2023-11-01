package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Multa;
import com.example.demo.domain.Veiculo;

public interface MultaRepository extends CrudRepository<Multa, Integer>{
	@Query("SELECT m FROM Multa m WHERE m.valor >= :valorMin")
	List<Multa> findByQtdMaior(@Param("valorMin") Double valorMin);
	List<Multa> findByVeiculo(Veiculo veiculo);
}
