package com.gestion.empresarial.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.empresarial.ExeptionApi;
import com.gestion.empresarial.enums.TipoPersona;
import com.gestion.empresarial.model.PersonaFisica;
import com.gestion.empresarial.model.PersonaJuridica;
import com.gestion.empresarial.repository.PersonaFisicaRepository;
import com.gestion.empresarial.repository.PersonaRepository;
import com.gestion.empresarial.service.ControlAccesoEndPointService;
import com.gestion.empresarial.service.PersonaUserService;

@Controller
@RestController
@RequestMapping("/persona")
public class PersonaController {
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private PersonaUserService personaUserService;
	
	@Autowired
	private PersonaFisicaRepository personaFisicaRepository;
	
	@Autowired
	private ControlAccesoEndPointService controlAccesoEndPointService;
	
	@ResponseBody
	@PostMapping("/salvarPersonaJuridica")
	public ResponseEntity<PersonaJuridica> salvarPersonaJurica(@RequestBody @Valid PersonaJuridica personaJuridica) throws ExeptionApi{
		
		if(personaJuridica == null) {
			throw new ExeptionApi("Persona Jurica no puede ser NUll");
		}
		if(personaJuridica.getTipoPersona() == null) {
			throw new ExeptionApi("Por favor informe que tipo de Persona es: Jurica o Proveedor");
		}
		if(personaJuridica.getId() == null && personaRepository.buscarPersonaJuridicaPorNit(personaJuridica.getNit()) != null) {
			throw new ExeptionApi("Ya existe un registro con el Nit: " + personaJuridica.getNit());
		}
		if(personaJuridica.getId() == null && personaRepository.buscarPersonaJuridicaPorEmail(personaJuridica.getEmail()) != null) {
			throw new ExeptionApi("Ya existe un registro con el Email: " + personaJuridica.getEmail());
		}
		
		personaJuridica = personaUserService.salvarPersonaJuridica(personaJuridica);
		
		controlAccesoEndPointService.actualizarEndPointSalvarPersonaJuridica();
		
		return new ResponseEntity<PersonaJuridica>(personaJuridica, HttpStatus.OK);
		
	}
	
	@ResponseBody
	@PostMapping("/salvarPersonaFisica")
	public ResponseEntity<PersonaFisica> salvarPersonaFisica(@RequestBody @Valid PersonaFisica personaFisica) throws ExeptionApi{
		
		if(personaFisica == null) {
			throw new ExeptionApi("Persona Fisica no puede ser NUll");
		}
		if(personaFisica.getTipoPersona() == null) {
			personaFisica.setTipoPersona(TipoPersona.NATURAL.name());
		}
		if(personaFisica.getId() == null && personaFisicaRepository.buscarPersonaFisicaPorNit(personaFisica.getNroIdentificacion()) != null) {
			throw new ExeptionApi("Ya existe un registro con el numero de identifiacacion: " + personaFisica.getNroIdentificacion());
		}
		if(personaFisica.getId() == null && personaFisicaRepository.buscarPersonaFisicaPorEmail(personaFisica.getEmail()) != null) {
			throw new ExeptionApi("Ya existe un registrada con el Email: " + personaFisica.getEmail());
		}
		
		personaFisica = personaUserService.salvarPersonaFisica(personaFisica);
		
		return new ResponseEntity<PersonaFisica>(personaFisica, HttpStatus.OK);
		
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPersonaFisicaPorPrimerNombre/{dato}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<PersonaFisica>> buscarPersonaFisicaPorPrimerNombre(@PathVariable("dato") String dato) { /*Recibe JSON y lo comvierte para objeto*/
	
		List<PersonaFisica> personasFisica = personaFisicaRepository.buscarPersonaFisicaPorPrimerNombre(dato.toUpperCase());
		
		return new ResponseEntity<List<PersonaFisica>>(personasFisica, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPersonaFisicaPorPrimerApellido/{dato}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<PersonaFisica>> buscarPersonaFisicaPorPrimerApellido(@PathVariable("dato") String dato) { /*Recibe JSON y lo comvierte para objeto*/
	
		List<PersonaFisica> personasFisica = personaFisicaRepository.buscarPersonaFisicaPorPrimerApellido(dato.toUpperCase());
		
		return new ResponseEntity<List<PersonaFisica>>(personasFisica, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping(value = "/buscarPersonaJuridicaPorRazonSocial/{dato}") /*Mapea URL para reibir JSON, los dos asteriscos significa que solo captura la url salvarAcceso 
		el resto lo omite**/
	public ResponseEntity<List<PersonaJuridica>> buscarPersonaJuridicaPorRazonSocial(@PathVariable("dato") String dato) { /*Recibe JSON y lo comvierte para objeto*/
	
		List<PersonaJuridica> personasJuridica = personaRepository.buscarPersonaJuridicaPorRazonSocial(dato.toUpperCase());
		
		return new ResponseEntity<List<PersonaJuridica>>(personasJuridica, HttpStatus.OK);
	
	}


}
