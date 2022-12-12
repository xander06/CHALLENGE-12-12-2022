package com.kruger.laboratorio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kruger.laboratorio.entity.Pacientes;
import com.kruger.laboratorio.repository.PacientesRepository;



@Service
@Primary
public class PacientessServiceImpl implements PacientesService {


	@Autowired
	private PacientesRepository repository;
	
	@Override
	@Transactional(readOnly = true) //siempre del  sprinboot 
	public List<Pacientes> Listar() {
				return (List<Pacientes>)repository.findAll(); //se hace el cast del list
	}

	@Override
	@Transactional(readOnly = true) //siempre del  sprinboot, es consulta por eso solo readonly
	public Optional<Pacientes> porId(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional 
	public Pacientes guardar(Pacientes paciente) {
		Pacientes pacientemp= new Pacientes();
		
		 if (repository.findById(paciente.getId()).isPresent()){
		 }
		 else if( validadorDeCedula(String.valueOf(paciente.getId()))) {
				pacientemp.setId(paciente.getId());
			}else {
				System.out.println("error  cedula incorrecta, corrija los datos"); 
			}
			pacientemp.setNombre(paciente.getNombre().toUpperCase());
			pacientemp.setApellido(paciente.getApellido().toUpperCase());
			pacientemp.setEmail(paciente.getEmail());
			
		return repository.save(pacientemp);
	}

	
	@Override
	public boolean validadorDeCedula(String cedula) {
		boolean cedulaCorrecta = false;
		
		try {		
			if (cedula.length() == 10) // ConstantesApp.LongitudCedula
			{
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {
					// Coeficientes de validación cédula
					// El decimo digito se lo considera dígito verificador
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula.substring(9,10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula.length() - 1); i++) {
						digito = Integer.parseInt(cedula.substring(i, i + 1))* coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}
		
					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					}
					else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
				cedulaCorrecta = false;
			}
			} catch (NumberFormatException nfe) {
				cedulaCorrecta = false;
			} catch (Exception err) {
				System.out.println("Una excepcion ocurrio en el proceso de validadcion");
				cedulaCorrecta = false;
			}
		
			if (!cedulaCorrecta) {
				System.out.println("La Cédula ingresada es Incorrecta");
			}
		return cedulaCorrecta;
		}
	
	@Override
	@Transactional 
	public void eliminar(Long id) {
		repository.deleteById(id);
	}

}
