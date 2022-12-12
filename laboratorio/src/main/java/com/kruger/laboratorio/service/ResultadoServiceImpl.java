package com.kruger.laboratorio.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.kruger.laboratorio.entity.Resultados;
import com.kruger.laboratorio.repository.PacientesRepository;
import com.kruger.laboratorio.repository.ResultadosRepository;

@Service
public class ResultadoServiceImpl implements ResultadoService {
	@Autowired
	ResultadosRepository repositoryres;
	@Autowired
	PacientesRepository repopaciente;

	@Override
	public List<Resultados> Listresult() {
		return (List<Resultados>) repositoryres.findAll();
		

	}

	@Override
	public Optional<Resultados> porId(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Resultados guardar(Resultados resultado) {
		Resultados saveresult= new Resultados();
		if( resultado!=null) {
			saveresult.setPorcentaje_azucar(resultado.getPorcentaje_azucar());
			saveresult.setPorcentaje_grasa (resultado.getPorcentaje_grasa());
			saveresult.setPorcentaje_oxigeno (resultado.getPorcentaje_oxigeno());
			saveresult.setRiesgo(calculoRiesgo(resultado));
			saveresult.setPaciente(resultado.getPaciente());
			saveresult.setFec_examen(resultado.getFec_examen());
					
			System.out.println("Resultado Almacenado correctamente");
		}else {
			System.out.println("error  cedula incorrecta, corrija los datos"); 
		}
	
	return repositoryres.save(saveresult);
		
	}

	public String calculoRiesgo(Resultados resultado) {
		
		if(resultado.getPorcentaje_azucar() > 70 & resultado.getPorcentaje_grasa() > 88.5 & resultado.getPorcentaje_oxigeno()< 60) {
			return ("ALTO");
		}else if (
				resultado.getPorcentaje_azucar() >= 50 & resultado.getPorcentaje_azucar() <= 70 
				& resultado.getPorcentaje_grasa() <= 88.5 & resultado.getPorcentaje_grasa() >= 62.2 
				& resultado.getPorcentaje_oxigeno()>= 60 & resultado.getPorcentaje_oxigeno() <= 70
				) {
			return("MEDIO");
		}else if (
				resultado.getPorcentaje_azucar() < 50
				& resultado.getPorcentaje_grasa() < 62.2
				& resultado.getPorcentaje_oxigeno() > 70
				) {
			return ("BAJO");
		}
		else {
		return	("NO SE ENCUENTRA DENTRO DE LOS PARAMETROS");}
	}
	
	
	@Override   //metodo para sacar todos los resultados de un mismo paciente
	public List<Resultados> listexp(Long id_paciente) {
		return repositoryres.datores();
}


	
}
