package com.gestion.empresarial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gestion.empresarial.model.PersonaFisica;

public interface PersonaFisicaRepository extends CrudRepository<PersonaFisica, Long>{

	@Query(value = "select pf from PersonaFisica pf where pf.nroIdentificacion = ?1")
	public List<PersonaFisica> buscarPersonaFisicaPorNit(String nit);
	
	@Query(value = "select pf from PersonaFisica pf where pf.nroIdentificacion = ?1")
	public List<PersonaFisica> buscarPersonasFisicaPorNit(String nit);
	
	@Query("select pf from PersonaFisica pf where upper(trim(pf.primerApellido)) like %?1%")
	List<PersonaFisica> buscarPersonaFisicaPorPrimerApellido(String desc);
	
	@Query("select pf from PersonaFisica pf where upper(trim(pf.primerNombre)) like %?1%")
	List<PersonaFisica> buscarPersonaFisicaPorPrimerNombre(String desc);
	
	@Query(value = "select pf from PersonaFisica pf where pf.email = ?1")
	public PersonaFisica buscarPersonaFisicaPorEmail(String email);
}
