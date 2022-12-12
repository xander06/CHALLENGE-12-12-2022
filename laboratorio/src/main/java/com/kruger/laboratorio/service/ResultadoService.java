package com.kruger.laboratorio.service;


import java.util.List;
import java.util.Optional;


import com.kruger.laboratorio.entity.Resultados;



public interface ResultadoService {
	List<Resultados> Listresult();
	Optional<Resultados> porId(Long id); //Evitar que el Objeto sea nulo y que sea null point exceptional
	Resultados guardar(Resultados resultado);//guardar para insertar o editar dependiendo del id y puede devolver el objeto no  devolver nada
	void eliminar(Long id);  //para eliminar no devuelve ningun objeto solo un verdadero o  falso
	List<Resultados> listexp (Long id_paciente);  //listar  resultados de acuerdo a numero de cedula de cliente
	
}
