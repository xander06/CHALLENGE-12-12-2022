package com.kruger.laboratorio.repository;


import org.springframework.data.repository.CrudRepository;


import com.kruger.laboratorio.entity.Pacientes;


public interface PacientesRepository extends CrudRepository <Pacientes, Long> {

}
