package com.kruger.laboratorio.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="Pacientes")
@Table(name="pacientes")
@NoArgsConstructor
@Data
public class Pacientes {

	@Id	@Column( unique = true)
	private Long id;
	private String nombre;
	private String apellido;
	@Column( unique = true)
	private String email;
	
	@OneToMany(mappedBy = "paciente")
	List<Resultados> result;
		 
}
