package com.kruger.laboratorio.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity(name="Resultados")
@Table(name="resultados")
@NoArgsConstructor
@Data
public class Resultados {

	@Id	
	@Column( unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private Double porcentaje_azucar;
	@NonNull
	
	private Double porcentaje_grasa;
	@NonNull
	private Double porcentaje_oxigeno;
	private String riesgo;
	@NonNull
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private LocalDateTime fec_examen;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="id_paciente")
	private Pacientes paciente;
}
