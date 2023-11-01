package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Veiculo;

public interface VeiculoRepository extends CrudRepository<Veiculo, String>{
	List<Veiculo> findByTipo(String tipo);
	List<Veiculo> findByAcessibilidade(Boolean acessibilidade);
	@Query("SELECT v FROM Veiculo v WHERE v.qtd_lugares >= :qtdMinimo")
	List<Veiculo> findByQtdMaior(@Param("qtdMinimo") int qtdMinimo);
	List<Veiculo> findBystatus(String _status);

}
