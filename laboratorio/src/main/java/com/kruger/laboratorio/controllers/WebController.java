package com.kruger.laboratorio.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.kruger.laboratorio.entity.Pacientes;
import com.kruger.laboratorio.entity.Resultados;
import com.kruger.laboratorio.service.PacientesService;
import com.kruger.laboratorio.service.ResultadoService;

@Controller
@RequestMapping("/app")
public class WebController {

	@Autowired
	PacientesService service; // esto hace una inyeccion
	@Autowired
	ResultadoService servresult;
	
	@GetMapping("/home") 
	public String home(){
		return  "index";		
	}
	@GetMapping("/listarpacientes") 
	public String listarpacientes(Model model){
		List <Pacientes> listpacientes=service.Listar();
		//agregar al modelo para la lista de los pacientes
		model.addAttribute("listpacientes",listpacientes);
		return "/views/pacientes/listPacientes";		
	}
	
	@GetMapping("/listaresultados") 
	public String listaresultados(Model model){
		List <Resultados> listresultados=servresult.Listresult();
		//agregar al modelo para la  lista de los resultados
		model.addAttribute("listresultados",listresultados);
		return "/views/resultados/listresultados";		
	}
	
	@GetMapping("/listresulpaciente") 
	public String listresulpaciente(@ModelAttribute Long id_paciente,Model model){
		List <Resultados> listresultados=servresult.listexp(id_paciente);
		//agregar al modelo para la  lista de los resultados
		model.addAttribute("listresultados",listresultados);
		return "/views/resultados/listresultados";		
	}
	
	@GetMapping("/crearpaciente") 
	public String frmcreatep(Model model){
		Pacientes cpaciente= new Pacientes();
		model.addAttribute("cpaciente",cpaciente);
		return "/views/pacientes/crearpaciente";		
	}
	
	@PostMapping("/guardarpaciente")
	public String guardarp(@ModelAttribute Pacientes paciente) {
		service.guardar((Pacientes) paciente);
		System.out.print("Cliente Guardado Con Exito");
		return "redirect:/app/listarpacientes";
	}
	
	@GetMapping("/crearexamen/{id}") 
	public String frmexamen( @PathVariable("id") Long id,Model model){
		Optional<Pacientes> opaciente =service.porId(id);
		Resultados svresultado= new Resultados();
		svresultado.setPaciente(opaciente.get());
		model.addAttribute("svresultado",svresultado);
		System.out.print("primer paso");
		
		return "/views/resultados/cexamen";		
	}
	
	@PostMapping("/guardarexamen")
	public String guardarp(@ModelAttribute Resultados resultado) {
		servresult.guardar(resultado);
		System.out.print("Resultado Guardado Con Exito");
		return "redirect:/app/listaresultados";
	}
}
