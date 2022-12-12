package com.kruger.laboratorio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.kruger.laboratorio.entity.Pacientes;
import com.kruger.laboratorio.entity.Resultados;
import com.kruger.laboratorio.repository.PacientesRepository;
import com.kruger.laboratorio.repository.ResultadosRepository;
import com.kruger.laboratorio.service.PacientessServiceImpl;
import com.kruger.laboratorio.service.ResultadoServiceImpl;

@SpringBootTest
class LaboratorioApplicationTests {
	@Mock
	private ResultadosRepository resultadosRepository;
	
	@InjectMocks
	private ResultadoServiceImpl resultadosServiceImpl; 
	
	@Mock
	private PacientesRepository pacienteRepository;
	
	@InjectMocks
	private PacientessServiceImpl pacienteServiceImpl; 
	

	@Test
	void contextLoads() {
		
		Iterable<Pacientes> lsp = Arrays.asList(
			new Pacientes(1002696951,"juan", "calle", "jc@hotmail.com"),
			new Pacientes(1002696951,"pedro", "jimenez", "jc@hotmail.com"),
			new Pacientes(1002696951,"pablo", "soto", "jc@hotmail.com"),
			new Pacientes(1002696951,"juanita", "de la calle", "jc@hotmail.com"));
		Mockito.when(pacienteRepository.findAll()).thenReturn(lsp);
		assertNotNull(pacienteServiceImpl.Listar());
		assertEquals(4, pacienteServiceImpl.Listar().size());
	}
	
	@Test
	void buscarexamenes() {
		
		Iterable<Resultados> lsr = Arrays.asList(
			new Resultados((long) 1,60.00,69.00,80.02,LocalDateTime.now()),
			new Resultados((long) 2,80.00,89.00,80.02,LocalDateTime.now()),
			new Resultados((long) 3,20.00,10.00,80.02,LocalDateTime.now()),
			new Resultados((long) 4,80.00,90.00,10.02,LocalDateTime.now()));
		Mockito.when(resultadosRepository.findAll()).thenReturn(lsr);
		assertNotNull(resultadosServiceImpl.Listresult());
		assertEquals(4, resultadosServiceImpl.Listresult().size());
	}
	
/*/	@Test
	void riesgo () {
		Mockito.when(resultadosServiceImpl.calculoRiesgo(new Resultados((long) 1,160.00,69.00,80.02,LocalDateTime.now())));
		assertNotNull(resultadosServiceImpl.calculoRiesgo(new Resultados((long) 1,160.00,69.00,80.02,LocalDateTime.now())));
		assertEquals("DATOS MAL INGRESADOS", resultadosServiceImpl.calculoRiesgo(new Resultados((long) 1,160.00,69.00,80.02,LocalDateTime.now())));
	}
*/	
}
