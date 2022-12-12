package com.kruger.laboratorio.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kruger.laboratorio.entity.Pacientes;
import com.kruger.laboratorio.entity.Resultados;
import com.kruger.laboratorio.service.PacientesService;
import com.kruger.laboratorio.service.ResultadoService;




@RestController // los controladores manejan las peticiones del usuario devuelve json o xml
@RequestMapping("/api") // ruta base, para empezar a crear el arbol de ruras
public class PacientesController {
	@Autowired
	PacientesService service; // esto hace una inyeccion
	@Autowired
	ResultadoService servresult;

	@GetMapping("/allpacientes")
	public List<Pacientes> listar() {
		return service.Listar();
	}

	@GetMapping("/paciente/{id}")
	public ResponseEntity<?> detalle(@PathVariable Long id) {
		Optional<Pacientes> pacientesOptional = service.porId(id);

		if (pacientesOptional.isPresent()) {
			return ResponseEntity.ok(pacientesOptional.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/guardarp")
	public ResponseEntity<?> crear (@RequestBody Pacientes paciente) { //guarda  un suaurio siempre y cuando se cumplan o coincidan todos los atributos con la clase
		return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(paciente));
	}

	@GetMapping("/allresultados")
	public List<Resultados> listresult(){
		return servresult.Listresult();
	}
	@PutMapping("/respaciente/{id_paciente}")
	public List <Resultados> listonceresult(@PathVariable Long id_paciente){
		return servresult.listexp(id_paciente);
	}
	
	@PostMapping("/guardarexamen")
	public void guardarexamen(@PathVariable Resultados resultado) {	
	servresult.guardar(resultado);
	System.out.print("Cliente Guardado Con Exito");
	
	}
}
