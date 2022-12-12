package com.kruger.laboratorio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.kruger.laboratorio.entity.Resultados;

public interface ResultadosRepository extends CrudRepository <Resultados, Long> {

	@Query("SELECT p FROM Pacientes p")
	List<Resultados> datores();
	
}
