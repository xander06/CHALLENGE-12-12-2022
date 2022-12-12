package com.kruger.laboratorio.service;

import java.util.List;
import java.util.Optional;



import com.kruger.laboratorio.entity.Pacientes;



public interface PacientesService {
	List<Pacientes> Listar();
	Optional<Pacientes> porId(Long id); //Evitar que el Objeto sea nulo y que sea null point exceptional
	Pacientes guardar(Pacientes paciente);//guardar para insertar o editar dependiendo del id y puede devolver el objeto no  devolver nada
	void eliminar(Long id);  //para eliminar no devuelve ningun objeto
	public boolean validadorDeCedula(String cedula) ;
}
